package com.mybatis.generator.bootstrap;

import org.mybatis.generator.api.ShellRunner;

/**
 * Created by lcs on 2017/8/17.
 */
public class NiuMybatisGen {

    public static void main(String[] args) throws Exception {
        NiuMybatisGen generator = new NiuMybatisGen();
        String rootPath = "C:\\Users\\lcs\\Desktop\\generate\\mybatis-generator-core-master1\\src\\main\\resources";
        String[] arg = new String[] {"-configfile", rootPath + "\\oracle\\generatorConfig-oracle.xml", "-overwrite"};
        ShellRunner.main(arg);
    }
}
