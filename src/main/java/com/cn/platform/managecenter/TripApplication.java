package com.cn.platform.managecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * User: wangyingxian
 * Date: 2019/04/16 17:51
 */
@SpringBootApplication
//默认扫描Application所在包和子包，这个工程要扫描webAuth下的内容，故需要设置。
@ComponentScan(basePackages = {"com.cn.platform.managecenter"})
@EnableTransactionManagement(proxyTargetClass = true)
public class TripApplication {
    public static void main(String[] args) {
        SpringApplication.run(TripApplication.class, args);
    }
}
