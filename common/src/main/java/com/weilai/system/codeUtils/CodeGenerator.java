package com.weilai.system.codeUtils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.sql.Types;
import java.util.Collections;


public class CodeGenerator {
    /**
     * 数据源配置
     */
    public static void main(String[] args) {
        String outputDir = Paths.get(System.getProperty("user.dir")) + "/src/main/java";
        String xmlDir = Paths.get(System.getProperty("user.dir")) + "/src/main/resources";

        String url = "jdbc:mysql://127.0.0.1:3306/blogtest?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true&allowMultiQueries=true&rewriteBatchedStatements=true" ;
        // 使用 FastAutoGenerator 快速配置代码生成器
        FastAutoGenerator.create(url, "root", "2270398619")
                .globalConfig(builder -> {
                    builder.author("miku") // 设置作者
                            .outputDir(outputDir)// 输出目录
                        ;
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT || typeCode == Types.TINYINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.weilai.common") // 设置父包名
                            .moduleName("modules")
                            .entity("entity") // 设置实体类包名
                            .mapper("mapper") // 设置 Mapper 接口包名
                            .service("service") // 设置 Service 接口包名
                            .serviceImpl("service.impl") // 设置 Service 实现类包名
                            .xml(xmlDir) // 设置 Mapper XML 文件包名.
                            .pathInfo(Collections.singletonMap(OutputFile.xml,xmlDir+"/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addTablePrefix("cc_") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok()
                            .idType(IdType.AUTO) // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .enableRestStyle() // 启用 REST 风格
                            .entityBuilder()
                            .naming(NamingStrategy.underline_to_camel) // 数据库字段映射到实体类的命名策略
                            .enableLombok() // 启用 Lombok
                            .formatFileName("%sEntity"); // 设置实体类命名格式，追加 Entity 后缀

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 模板引擎
                .execute(); // 执行生成
    }



}
