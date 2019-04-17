package com.cn.platform.managecenter.controller.log;

import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.entity.AjaxVo;
import com.cn.platform.managecenter.service.log.OperateLogService;
import com.cn.platform.managecenter.utils.PageParam;
import com.cn.platform.managecenter.utils.StringUtil;
import com.cn.platform.managecenter.utils.TableResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("omp/operateLog")
public class OperateLogController {
    private static Logger logger = LoggerFactory.getLogger(OperateLogController.class);

    @Autowired
    OperateLogService operateLogService;

    @GetMapping("/operateLogPage")
    public String  userPage(){
        return "omp/operateLogPage";
    }


    @ResponseBody
    @GetMapping("/operateLogPageList")
    public TableResult operateLogPageList(PageParam pageParam){
        TableResult tableResult=null;
        Map<String,Object> inMap= new HashMap<>();
        try{
            if(pageParam.getOtherParams()!=null){
                inMap=pageParam.getOtherParams();
            }
            tableResult =operateLogService.queryOperateLogListPage(pageParam.getPageNum(),pageParam.getPageSize(),inMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return tableResult;
    }


    @DeleteMapping("/deleteOperateLog")
    @ResponseBody
    public AjaxVo deleteOperateLog(@RequestParam String ids){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            Integer idArray[] = StringUtil.stringParseInt(ids);
            for (Integer id:idArray){
                operateLogService.deleteByPrimaryKey(Integer.valueOf(id));
            }
            ajaxVo.setCode(AjaxVo.SUCCESS);
            ajaxVo.setMsg(CommonConstant.DEFAULT_CN_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }


}
