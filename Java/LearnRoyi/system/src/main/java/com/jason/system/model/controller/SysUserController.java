package com.jason.system.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>描述:
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/11 22:37
 * @see
 */
/*
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
/**
  *
  *<p>描述:${Description}
  *
  *
  * <p>邮箱: fjz19971129@163.com
  * @version v1.0.0
  * @date：${DATE} ${HOUR}:${MINUTE}
  * @author 阿振
  * @see
public class ${NAME} {

        }
*/
@RestController
@RequestMapping("/system/user")
public class SysUserController {

}
