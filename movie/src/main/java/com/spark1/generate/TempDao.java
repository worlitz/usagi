package com.spark1.generate;

import com.spark1.mapper.Temp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TempDao {
    int deleteByPrimaryKey(String id);

    int insert(Temp record);

    int insertSelective(Temp record);

    Temp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Temp record);

    int updateByPrimaryKey(Temp record);

    int selectSize();
    List<Temp> selectPage(@Param("begin") Integer begin, @Param("size") Integer size);
}
