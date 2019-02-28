//package com.demo.shiro;
//
///**
// * CRUD 演示
// * @Auther: ZHANG.HAO
// * @Date: 2019-02-21 09:24
// * @Description:
// */
//
//import javax.annotation.Resource;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SampleTest {
//
//    @Resource
//    private SysUserMapper mapper;
//
//    @Test
//    public void aInsert() {
//        SysUser user = new SysUser();
//        user.setNickname("小羊");
//        user.setRoleId(3);
//        user.setUsername("abc@mp.com");
//        Assert.assertTrue(mapper.insert(user) > 0);
//        // 成功直接拿会写的 ID
//        System.err.println("\n插入成功 ID 为：" + user.getId());
//    }
//
//
//    @Test
//    public void bDelete() {
//        Assert.assertTrue(mapper.deleteById(3L) > 0);
//        Assert.assertTrue(mapper.delete(new QueryWrapper<SysUser>()
//                .lambda().eq(SysUser::getNickname, "Sandy")) > 0);
//    }
//
//
//    @Test
//    public void cUpdate() {
////        Assert.assertTrue(mapper.updateById(new SysUser().setId(1L+"").setEmail("ab@c.c")) > 0);
//        Assert.assertTrue(mapper.update(new SysUser().setUsername("mp"),
//                new UpdateWrapper<SysUser>().lambda()
//                        .set(SysUser::getUsername, 3)
//                        .eq(SysUser::getId, 2)) > 0);
//    }
//
//
//    @Test
//    public void dSelect() {
//        Assert.assertEquals("ab@c.c", mapper.selectById(1L).getNickname());
//        SysUser user = mapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getId, 2));
//        Assert.assertEquals("mp", user.getUsername());
//        Assert.assertTrue(3 == user.getRoleId());
//    }
//
//    @Test
//    public void orderBy() {
//        QueryWrapper<SysUser> ew = new QueryWrapper<>();
//        ew.orderByAsc("age");
//        Assert.assertTrue(!mapper.selectList(ew).isEmpty());
//    }
//
//    @Test
//    public void orderByLambda() {
//        LambdaQueryWrapper<SysUser> lw = new LambdaQueryWrapper<>();
//        lw.orderByAsc(SysUser::getCreateTime);
//        Assert.assertTrue(!mapper.selectList(lw).isEmpty());
//    }
//
//    @Test
//    public void testm(){
//        List<String> list = new ArrayList<String>();
//        list.add("");
//        list.add("123");
//        list.add("333");
//        for (Object n : list) { System.out.println(n); }
//
//        list.forEach(n -> System.out.println(n));
//
//        list.forEach(System.out::println);
//    }
//}
