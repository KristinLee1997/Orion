package com.aries.orion.mapper;

import com.aries.orion.model.po.AcQuestion;
import com.aries.orion.model.po.AcQuestionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AcQuestionMapper {
    int countByExample(AcQuestionExample example);

    int deleteByExample(AcQuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AcQuestion record);

    int insertSelective(AcQuestion record);

    List<AcQuestion> selectByExample(AcQuestionExample example);

    AcQuestion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AcQuestion record, @Param("example") AcQuestionExample example);

    int updateByExample(@Param("record") AcQuestion record, @Param("example") AcQuestionExample example);

    int updateByPrimaryKeySelective(AcQuestion record);

    int updateByPrimaryKey(AcQuestion record);
}