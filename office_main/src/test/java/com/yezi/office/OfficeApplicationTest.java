package com.yezi.office;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.yezi.office.acl.service.MenuService;
import com.yezi.office.mapper.UserMapper;
import com.yezi.office.pojo.User;
import com.yezi.office.pojo.vo.UserVo;
import com.yezi.office.service.UserService;
import com.yezi.office.utils.PassWorldUtils;
import com.yezi.office.utils.R;
import com.yezi.office.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 叶子
 * @Description 配置代码生成器
 * @PackageName com.yezi.office
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/29 星期二 15:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficeApplicationTest {
    @Autowired
    private UserService userService;
    @Resource
    UserMapper mapper;
    @Autowired
    private MenuService menuService;

    /**
     * 代码生成器
     */
    @Test
    public void create(){
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//得到当前文件夹路径
        gc.setOutputDir(projectPath + "/src/main/java");//代码生成目录
        gc.setAuthor("叶子");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
        gc.setIdType(IdType.ID_WORKER_STR); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/office");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.yezi");
        pc.setModuleName("office"); //模块名
        pc.setController("controller");
        pc.setEntity("pojo");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("office_clock");//表名称
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }

    @Test
    public void test(){
        for (int i = 0; i < 3; i++) {
            System.out.println(StringUtils.getId());
        }
    }

    @Test
    public void randMD5(){
        System.out.println(new PassWorldUtils().encode("123456789"));
    }

    @Test
    public void test2(){
        for (User user : userService.list()) {
            System.out.println(user);
        }
    }

    @Test
    public void timeTest(){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String 日期 = now.format(formatter);
        String start = now.format(formatter) + " 8:00:00";
        String end = now.format(formatter) + " 9:00:00";
        // 打卡开始时间
        Date startTime = DateUtil.parse(start, "yyyy-MM-dd HH:mm:ss");
        // 打卡结束时间
        Date endTime = DateUtil.parse(end, "yyyy-MM-dd HH:mm:ss");

        System.out.println(startTime);
        System.out.println(endTime);
        // 当前时间
        Date nowTime = DateUtil.parse(DateUtil.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(nowTime);
        boolean rs = nowTime.after(startTime) && nowTime.before(endTime);
        System.out.println(rs);
    }

    Date getOrderTime(String order){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String 日期 = now.format(formatter);
        String time = now.format(formatter) + order;

        return DateUtil.parse(time, "yyyy-MM-dd HH:mm:ss");
    }

    @Test
    public void getOrderTimeTest(){
        // 打卡开始时间
        Date startTime = getOrderTime(" 8:00:00");
        // 打卡结束时间
        Date endTime = getOrderTime(" 9:00:00");

        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void listByAutherTest(){
        System.out.println(R.ok().data("test",menuService.listByAuther("1346328088017047553")).toString());
    }

    @Test
    public void treeMenuTest(){
        System.out.println(menuService.treeMenu());
    }
}
