package com.cn.platform.managecenter.auth;


import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.entity.system.Perms;
import com.cn.platform.managecenter.service.system.PermsService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShiroFilerChainManager {
    @Autowired
    PermsService permsService;
    @Autowired(required = false)
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    /**
     * 容器加载完成后自动执行
     */
    @PostConstruct
    public void init() {
        updatePermission();
    }


    /**
     * 更新权限信息
     */
    public void updatePermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

            // 重新构建生成
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
    }

    /**
     * 从数据库中查询权限信息
     */
    public Map<String, String> loadFilterChainDefinitions() {
     Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        //anon表示可以匿名访问
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/loginUp","anon");
        filterChainDefinitionMap.put("/logOut","anon");
        filterChainDefinitionMap.put("/403","anon");
        filterChainDefinitionMap.put("/404","anon");
        filterChainDefinitionMap.put("/error","anon");
        filterChainDefinitionMap.put("/randomImg","anon");
        filterChainDefinitionMap.put("/forgetPwdPage","anon");
        filterChainDefinitionMap.put("/sendPhoneMsg","anon");
        filterChainDefinitionMap.put("/validPhoneMsg","anon");
        filterChainDefinitionMap.put("/actuator/health","anon");

        filterChainDefinitionMap.put("/getMonitorInfo","anon");

        filterChainDefinitionMap.put("/static/**", "anon");

        Map<String,Object> map = new HashMap<>();
        map.put("permType", CommonConstant.CONTROL_DATA_TYPE);
        List<Perms> perms = permsService.selectAllPerms(map);
        for (Perms p : perms){
            String uri = p.getUri();
            String permission = p.getPerm();
            if(StringUtils.isNotBlank(uri)&&StringUtils.isNotBlank(permission)){
                filterChainDefinitionMap.put(uri,"perms["+permission+"]");
            }
        }
        filterChainDefinitionMap.put("/**", "user");
        //authc表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

}  