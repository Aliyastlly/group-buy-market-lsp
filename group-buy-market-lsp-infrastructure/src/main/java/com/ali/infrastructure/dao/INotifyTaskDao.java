package com.ali.infrastructure.dao;

import com.ali.infrastructure.dao.po.NotifyTask;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface INotifyTaskDao {

    void insert(NotifyTask notifyTask);

}
