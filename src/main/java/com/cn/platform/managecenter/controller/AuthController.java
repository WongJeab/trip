package com.cn.platform.managecenter.controller;

import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.entity.system.User;
import com.cn.platform.managecenter.service.system.UserService;
import com.cn.platform.managecenter.utils.OutMsg;
import com.cn.platform.managecenter.utils.ShiroUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by dhj on 2018/8/8.
 */
@Controller
public class AuthController extends BaseController {
    @Autowired

    private UserService userService;

    @GetMapping("/login")
    public String  login(){
        return "auth/login";

    }
    @GetMapping("/index")
    public String  index(){
        return "index";

    }
    @GetMapping("/main")
    public String  main(){
        return "main";

    }
    @GetMapping("/403")
    public String  noPerm(){
        return "403";

    }


    @PostMapping("/loginUp")
    @ResponseBody
    public OutMsg<String> loginUp(@RequestParam String username, @RequestParam String password, @RequestParam  String randImg){
        OutMsg<String> outMsg = new OutMsg<String>();
        String msg = "";
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username, ShiroUtil.sha256Hash(password));
        Subject subject = SecurityUtils.getSubject();
        try {
            if(StringUtils.isNotBlank(validateRancode(randImg))){
                return   outMsg.fail(validateRancode(randImg),"","");
            }
            //session超时时间30分钟
            subject.getSession().setTimeout(1800000);
            subject.login(usernamePasswordToken);
        } catch (IncorrectCredentialsException e) {
            msg = CommonConstant.PASSWORD_ERROR;
        } catch (LockedAccountException e) {
            msg = CommonConstant.ACCOUNT_LOCKED;
        } catch (UnknownAccountException e) {
            msg = CommonConstant.ACCOUNT_NOT_EXISITS;
        } catch (UnauthorizedException e) {
            msg = CommonConstant.AUTH_NOT_ENOUGH;
        } catch (Exception e){
            msg = CommonConstant.SYS_ERROR;
        }
        if(subject.isAuthenticated()){
            //这里获取到的TfImsUser中包含用户、角色、权限（资源）信息
            User user=(User) subject.getPrincipal();
            subject.getSession().setAttribute(CommonConstant.USER_KEY,user);
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            // 获取保存的URL
            if (savedRequest == null || savedRequest.getRequestUrl() == null) {
                return outMsg.success(CommonConstant.LOGIN_SUCCESS,"index","");
            } else {
                String url = savedRequest.getRequestUrl();
                return outMsg.success(CommonConstant.LOGIN_SUCCESS,url,"");
            }
        }
        return outMsg.fail(msg,"","");
    }
    @GetMapping ( "/logOut")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    /**
     * 验证码
     *
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/randomImg")
    public void randomImg() throws Exception {
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 在内存中创建图象
        int width = 80, height = 35;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(new Color(139,201,242));
        //g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        // 取随机产生的认证码(6位数字)
        String sRand = "";
        String s = "012345678901234567890123456789012345678901234567890123456789";
        for (int i = 0; i < 4; i++) {
            char rand = s.charAt(random.nextInt(s.length()));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(String.valueOf(rand), 13 * i + 12, 27);
        }
        g.drawOval(5, 10, 60, 15);
        // 图象生效
        g.dispose();
        request.getSession().setAttribute(CommonConstant.YZM_CODE, sRand);
        ServletOutputStream output;
        try {
            output = response.getOutputStream();
            // 输出图象到页面
            ImageIO.write(image, "JPEG", output);
        } catch (IOException e) {

        }
    }
    /**
     * 生成随机颜色
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    private String validateRancode(String rancode)  {
        if (StringUtils.isBlank(rancode)) {
            return CommonConstant.YZM_CODE_NULL;
        }
        String sessionRancode="";
        try{
            sessionRancode = (String) request.getSession().getAttribute(CommonConstant.YZM_CODE);
        }catch(Exception e){
            e.printStackTrace();
            return CommonConstant.YZM_CODE_ERROR;
        }
        if (!sessionRancode.equals(rancode)) {
            return CommonConstant.YZM_CODE_ERROR;
        }
        request.getSession().removeAttribute(CommonConstant.YZM_CODE);
        return null;
    }



}
