package com.maker.vo;

import lombok.*;

/**
 * Lombok,它可以通过注解的形式，帮用户生成相关的类结构
 *  Lombok相关依赖：
 *      compileOnly group: 'org.projectlombok', name: 'lombok', version: '1.18.20'
 *  在microboot dependencies.gradle中定义要使用的依赖
 *  由于各个子项目都有可能要使用Lombok插件，那么最佳的做法就是在公共依赖库中(build.gradle中的subproject)进行lombok插件的配置
 *  此时配置需要准备两种不同的环境：编译生效，注解生效
 *  进行完以上项目依赖环境的配置后，还需要添加idea中的插件依赖File | Settings | Plugins，搜索lombok，安装后使其生效
 *  进入File | Settings | Build, Execution, Deployment | Compiler | Annotation Processors中，勾上Enable Annotation processing
 *
 *  添加了相应的lombok注解后，可以使用反编译工具，对Dept.class文件进行验证
 *  jd-gui目前不支持JDK17的反编译，无法打开
 *  jad不支持java新特性
 *  CFR可以反编译成功，安装在本机的：D:\JD-gui目录下，是一个jar文件，使用方式为：java -jar D:\JD-gui\cfr-0.152.jar Dept.class
 *
 *  Dept.class文件反编译结果如下：
 *      ackage com.maker.com.maker.vo;
 *
 * public class Dept {
 *     private Long deptno;
 *     private String deptname;
 *     private String loc;
 *
 *     public Long getDeptno() {
 *         return this.deptno;
 *     }
 *
 *     public String getDeptname() {
 *         return this.deptname;
 *     }
 *
 *     public String getLoc() {
 *         return this.loc;
 *     }
 *
 *     public void setDeptno(Long deptno) {
 *         this.deptno = deptno;
 *     }
 *
 *     public void setDeptname(String deptname) {
 *         this.deptname = deptname;
 *     }
 *
 *     public void setLoc(String loc) {
 *         this.loc = loc;
 *     }
 *
 *     public boolean equals(Object o) {
 *         if (o == this) {
 *             return true;
 *         }
 *         if (!(o instanceof Dept)) {
 *             return false;
 *         }
 *         Dept other = (Dept)o;
 *         if (!other.canEqual(this)) {
 *             return false;
 *         }
 *         Long this$deptno = this.getDeptno();
 *         Long other$deptno = other.getDeptno();
 *         if (this$deptno == null ? other$deptno != null : !((Object)this$deptno).equals(other$deptno)) {
 *             return false;
 *         }
 *         String this$deptname = this.getDeptname();
 *         String other$deptname = other.getDeptname();
 *         if (this$deptname == null ? other$deptname != null : !this$deptname.equals(other$deptname)) {
 *             return false;
 *         }
 *         String this$loc = this.getLoc();
 *         String other$loc = other.getLoc();
 *         return !(this$loc == null ? other$loc != null : !this$loc.equals(other$loc));
 *     }
 *
 *     protected boolean canEqual(Object other) {
 *         return other instanceof Dept;
 *     }
 *
 *     public int hashCode() {
 *         int PRIME = 59;
 *         int result = 1;
 *         Long $deptno = this.getDeptno();
 *         result = result * 59 + ($deptno == null ? 43 : ((Object)$deptno).hashCode());
 *         String $deptname = this.getDeptname();
 *         result = result * 59 + ($deptname == null ? 43 : $deptname.hashCode());
 *         String $loc = this.getLoc();
 *         result = result * 59 + ($loc == null ? 43 : $loc.hashCode());
 *         return result;
 *     }
 *
 *     public String toString() {
 *         return "Dept(deptno=" + this.getDeptno() + ", deptname=" + this.getDeptname() + ", loc=" + this.getLoc() + ")";
 *     }
 *
 *  @SneakyThrows:帮助用户手动处理异常，一般用在方法上，自动生成try...catch
 *  @Cleanup InputStram input;
 *     Cleanup注解，Lombok提供，可以帮助程序关闭IO流程（同样可以关闭数据库，网络编程等连接流）
 *
 *  @Synchronized:多线程编程的时候，实现同步，防止出现结果错误，但是该注解只实现了最基础的同步，有可能出现死锁的可能。
 *
 * */
@Data//lombok注解，使用较为频繁
@NoArgsConstructor//在使用了NonNull注解后，还想要生成无参构造就可以使用该注解，要求自动生成无参构造
@AllArgsConstructor//生成全参构造
public class Dept {
    @NonNull//deptno不允许为空，它会生成一个有参构造，并且必须得传入deptno属性，一旦要生成无参构造，该注解就失效了
    private Long deptno;
    private String deptname;
    private String loc;



}
