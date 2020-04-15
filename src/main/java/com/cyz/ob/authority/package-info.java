
/**
 * 
 * 
 * 权限相关逻辑包
 * 
 * 
 * 本系统将权限 分为   用户 - 角色  - 权限  - 资源  
 * 
 * 用户 即与角色关联 也与权限关联， 但最终校验的时候只会校验角色-权限表， 
 * 用户与角色关联的作用在于，因为角色与权限是一对多的关系，相当于一个权限组，这样当我们就可以通过
 * 实现创建好角色，关联权限，然后通过给用户赋予角色来批量添加权限，就不用每次都一个一个权限的添加。
 * 权限与资源也是一对多的关系。
 * @author cyz
 *
 */
package com.cyz.ob.authority;
