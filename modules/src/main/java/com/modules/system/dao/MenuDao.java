//package com.modules.system.dao;
//
//import com.common.model.dto.user.Menu;
//import org.apache.ibatis.annotations.*;
//import org.mapstruct.Mapper;
//
//import java.util.List;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2019/11/07
// **/
//@Mapper
//public interface MenuDao {
//    /***
//     * add
//     * @param menu
//     * @return
//     */
//    @Insert("<script>"
//            + "<selectKey keyColumn='id' resultType='java.lang.Long' keyProperty='id' order='AFTER'>"
//            + "SELECT LAST_INSERT_ID()"
//            + "</selectKey>"
//            + "insert into     sys_menu"
//                + "<set>"
//                    + "<if test='name != null'> name=#{name},</if>"
//                    + "<if test='desc != null'>description=#{desc},</if>"
//                    + "<if test='url != null'>url=#{url}, </if>"
//                    + "<if test='icon != null'>icon=#{icon}, </if>"
//                    + "<if test='userId != null'>user_id=#{userId}, </if>"
//                    + "<if test='type != null'>type=#{type}, </if>"
//                    + "<if test='pId != null'>parent_id=#{pId}, </if>"
//                    + "create_date=now(),"
//                    + "status = 'Y'"
//                + "</set>"
//            +"</script>")
//    int add(@Param("menu") Menu menu);
//
//    /***
//     * delete
//     * @param menu
//     * @return
//     */
//    @Delete("<script></script>")
//    int delete(@Param("menu")Menu menu);
//
//    /***
//     * update
//     * @param menu
//     * @return
//     */
//    @Update("<script>"
//            + "update  sys_menu"
//                + "<set>"
//                    + "<if test='name != null'>name = #{name}, </if>"
//                    + "<if test='desc != null'>description = #{desc}, </if>"
//                    + "<if test='url != null'> url = #{url}, </if>"
//                    + "<if test='icon != null'> icon = #{icon}, </if>"
//                    + "<if test='userId != null'> user_id = #{userId}, </if>"
//                    + "<if test='type != null'> type = #{type}, </if>"
//                    + "<if test='status != null'> status = #{status}, </if>"
//                    + "update_date=now()"
//                + "</set>"
//                + "<where>id=#{id}</where>"
//            + "</script>")
//    int update(@Param("menu")Menu menu);
//
//    /***
//     * find
//     * @param menu
//     * @return
//     */
//    @Select("<script></script>")
//    @ResultType(Menu.class)
//    List<Menu> find(@Param("menu")Menu menu);
//}
