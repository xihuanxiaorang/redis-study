package top.xiaorang.redis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MybatisPlusAutoGenerator {
    private static final String URL =
            "jdbc:mysql://localhost:3306/redis?useSSL=false&useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        FastAutoGenerator.create(new DataSourceConfig.Builder(URL, USERNAME, PASSWORD))
                .globalConfig(
                        builder ->
                                builder
                                        .author("xiaorang")
                                        .fileOverride()
                                        .disableOpenDir()
                                        .commentDate("yyyy-MM-dd HH:mm:ss")
                                        .dateType(DateType.ONLY_DATE)
                                        .outputDir(projectPath + "/src/main/java"))
                .packageConfig(
                        builder ->
                                builder
                                        .parent("top.xiaorang.redis")
                                        .pathInfo(
                                                Collections.singletonMap(
                                                        OutputFile.mapperXml, projectPath + "/src/main/resources/mapper")))
                .strategyConfig(
                        (scanner, builder) ->
                                builder
                                        .addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                                        .addTablePrefix("t_")
                                        .controllerBuilder()
                                        .enableRestStyle()
                                        .enableHyphenStyle()
                                        .serviceBuilder()
                                        .formatServiceFileName("%sService")
                                        .formatServiceImplFileName("%sServiceImpl")
                                        .mapperBuilder()
                                        .enableMapperAnnotation()
                                        .entityBuilder()
                                        .enableLombok()
                                        .versionColumnName("version")
                                        .logicDeleteColumnName("deleted")
                                        .idType(IdType.AUTO)
                                        .addTableFills(
                                                new Column("create_time", FieldFill.INSERT),
                                                new Column("update_time", FieldFill.INSERT_UPDATE))
                                        .build())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
