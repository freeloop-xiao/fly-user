package com.fly;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: xiao kun
 * Date: 2019-04-25
 * Time: 08:40
 */
public class CodeGenerator {

    private static final String OUTPUT_DIR = "/src/main/java";

//
//    private static final String DB_USER_NAME = "root";
//
//    private static final String DB_PASSWORD = "Freeloop123!";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/admin?useUnicode=true&useSSL=false&characterEncoding=utf8&&serverTimezone=UTC";

    private static final String DB_USER_NAME = "root";

    private static final String DB_PASSWORD = "freeloop";

    private static final String PARENT_PACKAGE = "com.fly.user";

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 代码生成路径
        String projectPath = "D:\\workspace\\fly-user";
        gc.setOutputDir(projectPath + OUTPUT_DIR);
        gc.setAuthor("free loop");
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setServiceName("%sService");
        generator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(DB_URL);
        dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(DB_USER_NAME);
        dsc.setPassword(DB_PASSWORD);
        generator.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 所创建包的父包名
        pc.setParent(PARENT_PACKAGE);
        pc.setModuleName(scanner("模块名"));
        generator.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass("BaseController");
        strategy.setSuperControllerClass("com.project.fly.common.web.controller.BaseController");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        generator.setStrategy(strategy);
        generator.execute();
    }


    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}