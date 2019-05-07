package com.aries.orion.model.po;

import java.util.ArrayList;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNull() {
            addCriterion("teacher_name is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNotNull() {
            addCriterion("teacher_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameEqualTo(String value) {
            addCriterion("teacher_name =", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotEqualTo(String value) {
            addCriterion("teacher_name <>", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThan(String value) {
            addCriterion("teacher_name >", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_name >=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThan(String value) {
            addCriterion("teacher_name <", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("teacher_name <=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLike(String value) {
            addCriterion("teacher_name like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotLike(String value) {
            addCriterion("teacher_name not like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIn(List<String> values) {
            addCriterion("teacher_name in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotIn(List<String> values) {
            addCriterion("teacher_name not in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameBetween(String value1, String value2) {
            addCriterion("teacher_name between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotBetween(String value1, String value2) {
            addCriterion("teacher_name not between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherDescIsNull() {
            addCriterion("teacher_desc is null");
            return (Criteria) this;
        }

        public Criteria andTeacherDescIsNotNull() {
            addCriterion("teacher_desc is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherDescEqualTo(String value) {
            addCriterion("teacher_desc =", value, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescNotEqualTo(String value) {
            addCriterion("teacher_desc <>", value, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescGreaterThan(String value) {
            addCriterion("teacher_desc >", value, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_desc >=", value, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescLessThan(String value) {
            addCriterion("teacher_desc <", value, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescLessThanOrEqualTo(String value) {
            addCriterion("teacher_desc <=", value, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescLike(String value) {
            addCriterion("teacher_desc like", value, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescNotLike(String value) {
            addCriterion("teacher_desc not like", value, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescIn(List<String> values) {
            addCriterion("teacher_desc in", values, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescNotIn(List<String> values) {
            addCriterion("teacher_desc not in", values, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescBetween(String value1, String value2) {
            addCriterion("teacher_desc between", value1, value2, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andTeacherDescNotBetween(String value1, String value2) {
            addCriterion("teacher_desc not between", value1, value2, "teacherDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescIsNull() {
            addCriterion("course_desc is null");
            return (Criteria) this;
        }

        public Criteria andCourseDescIsNotNull() {
            addCriterion("course_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCourseDescEqualTo(String value) {
            addCriterion("course_desc =", value, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescNotEqualTo(String value) {
            addCriterion("course_desc <>", value, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescGreaterThan(String value) {
            addCriterion("course_desc >", value, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescGreaterThanOrEqualTo(String value) {
            addCriterion("course_desc >=", value, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescLessThan(String value) {
            addCriterion("course_desc <", value, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescLessThanOrEqualTo(String value) {
            addCriterion("course_desc <=", value, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescLike(String value) {
            addCriterion("course_desc like", value, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescNotLike(String value) {
            addCriterion("course_desc not like", value, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescIn(List<String> values) {
            addCriterion("course_desc in", values, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescNotIn(List<String> values) {
            addCriterion("course_desc not in", values, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescBetween(String value1, String value2) {
            addCriterion("course_desc between", value1, value2, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseDescNotBetween(String value1, String value2) {
            addCriterion("course_desc not between", value1, value2, "courseDesc");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceIsNull() {
            addCriterion("course_introduce is null");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceIsNotNull() {
            addCriterion("course_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceEqualTo(String value) {
            addCriterion("course_introduce =", value, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceNotEqualTo(String value) {
            addCriterion("course_introduce <>", value, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceGreaterThan(String value) {
            addCriterion("course_introduce >", value, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("course_introduce >=", value, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceLessThan(String value) {
            addCriterion("course_introduce <", value, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceLessThanOrEqualTo(String value) {
            addCriterion("course_introduce <=", value, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceLike(String value) {
            addCriterion("course_introduce like", value, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceNotLike(String value) {
            addCriterion("course_introduce not like", value, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceIn(List<String> values) {
            addCriterion("course_introduce in", values, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceNotIn(List<String> values) {
            addCriterion("course_introduce not in", values, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceBetween(String value1, String value2) {
            addCriterion("course_introduce between", value1, value2, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andCourseIntroduceNotBetween(String value1, String value2) {
            addCriterion("course_introduce not between", value1, value2, "courseIntroduce");
            return (Criteria) this;
        }

        public Criteria andBrightSpotIsNull() {
            addCriterion("bright_spot is null");
            return (Criteria) this;
        }

        public Criteria andBrightSpotIsNotNull() {
            addCriterion("bright_spot is not null");
            return (Criteria) this;
        }

        public Criteria andBrightSpotEqualTo(String value) {
            addCriterion("bright_spot =", value, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotNotEqualTo(String value) {
            addCriterion("bright_spot <>", value, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotGreaterThan(String value) {
            addCriterion("bright_spot >", value, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotGreaterThanOrEqualTo(String value) {
            addCriterion("bright_spot >=", value, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotLessThan(String value) {
            addCriterion("bright_spot <", value, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotLessThanOrEqualTo(String value) {
            addCriterion("bright_spot <=", value, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotLike(String value) {
            addCriterion("bright_spot like", value, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotNotLike(String value) {
            addCriterion("bright_spot not like", value, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotIn(List<String> values) {
            addCriterion("bright_spot in", values, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotNotIn(List<String> values) {
            addCriterion("bright_spot not in", values, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotBetween(String value1, String value2) {
            addCriterion("bright_spot between", value1, value2, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andBrightSpotNotBetween(String value1, String value2) {
            addCriterion("bright_spot not between", value1, value2, "brightSpot");
            return (Criteria) this;
        }

        public Criteria andImageIdIsNull() {
            addCriterion("image_id is null");
            return (Criteria) this;
        }

        public Criteria andImageIdIsNotNull() {
            addCriterion("image_id is not null");
            return (Criteria) this;
        }

        public Criteria andImageIdEqualTo(Long value) {
            addCriterion("image_id =", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotEqualTo(Long value) {
            addCriterion("image_id <>", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdGreaterThan(Long value) {
            addCriterion("image_id >", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("image_id >=", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdLessThan(Long value) {
            addCriterion("image_id <", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdLessThanOrEqualTo(Long value) {
            addCriterion("image_id <=", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdIn(List<Long> values) {
            addCriterion("image_id in", values, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotIn(List<Long> values) {
            addCriterion("image_id not in", values, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdBetween(Long value1, Long value2) {
            addCriterion("image_id between", value1, value2, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotBetween(Long value1, Long value2) {
            addCriterion("image_id not between", value1, value2, "imageId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}