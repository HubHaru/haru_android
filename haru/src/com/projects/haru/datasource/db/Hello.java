package com.projects.haru.datasource.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HongjinKim (audiosignal@gmail.com)
 * Date: 7/8/14
 * Time: 3:56 AM
 */
// TODO: ActiveAndroid Model 객체로 Queryset, HelloTable, DbApi를 대체할 수 있으니 적용 여부 고민할 것
@Table(name = "Hello")
public class Hello extends Model {
    @Column(name = "Id")
    public Long id;

    @Column(name = "Title")
    public String title;

    @Column(name = "Contnet")
    public String content;

    public static Hello selectById(long id) {
        return new Select().from(Hello.class).where("Id = ?", id).executeSingle();
    }

    public static List<Hello> selectAll() {
        return new Select().from(Hello.class).orderBy("Id ASC").execute();
    }
}
