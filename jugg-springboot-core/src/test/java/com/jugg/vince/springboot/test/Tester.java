package com.jugg.vince.springboot.test;

import com.jugg.vince.springboot.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * Author:   Vince
 * Date:     2018/7/25 上午10:14
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class Tester {

}
