package com.sec.aidog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LastnecareabackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LastnecareabackExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRealtimeIsNull() {
            addCriterion("realtime is null");
            return (Criteria) this;
        }

        public Criteria andRealtimeIsNotNull() {
            addCriterion("realtime is not null");
            return (Criteria) this;
        }

        public Criteria andRealtimeEqualTo(Date value) {
            addCriterion("realtime =", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeNotEqualTo(Date value) {
            addCriterion("realtime <>", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeGreaterThan(Date value) {
            addCriterion("realtime >", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("realtime >=", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeLessThan(Date value) {
            addCriterion("realtime <", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeLessThanOrEqualTo(Date value) {
            addCriterion("realtime <=", value, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeIn(List<Date> values) {
            addCriterion("realtime in", values, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeNotIn(List<Date> values) {
            addCriterion("realtime not in", values, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeBetween(Date value1, Date value2) {
            addCriterion("realtime between", value1, value2, "realtime");
            return (Criteria) this;
        }

        public Criteria andRealtimeNotBetween(Date value1, Date value2) {
            addCriterion("realtime not between", value1, value2, "realtime");
            return (Criteria) this;
        }

        public Criteria andNecIdIsNull() {
            addCriterion("nec_id is null");
            return (Criteria) this;
        }

        public Criteria andNecIdIsNotNull() {
            addCriterion("nec_id is not null");
            return (Criteria) this;
        }

        public Criteria andNecIdEqualTo(String value) {
            addCriterion("nec_id =", value, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdNotEqualTo(String value) {
            addCriterion("nec_id <>", value, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdGreaterThan(String value) {
            addCriterion("nec_id >", value, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdGreaterThanOrEqualTo(String value) {
            addCriterion("nec_id >=", value, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdLessThan(String value) {
            addCriterion("nec_id <", value, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdLessThanOrEqualTo(String value) {
            addCriterion("nec_id <=", value, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdLike(String value) {
            addCriterion("nec_id like", value, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdNotLike(String value) {
            addCriterion("nec_id not like", value, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdIn(List<String> values) {
            addCriterion("nec_id in", values, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdNotIn(List<String> values) {
            addCriterion("nec_id not in", values, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdBetween(String value1, String value2) {
            addCriterion("nec_id between", value1, value2, "necId");
            return (Criteria) this;
        }

        public Criteria andNecIdNotBetween(String value1, String value2) {
            addCriterion("nec_id not between", value1, value2, "necId");
            return (Criteria) this;
        }

        public Criteria andPowerIsNull() {
            addCriterion("power is null");
            return (Criteria) this;
        }

        public Criteria andPowerIsNotNull() {
            addCriterion("power is not null");
            return (Criteria) this;
        }

        public Criteria andPowerEqualTo(String value) {
            addCriterion("power =", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotEqualTo(String value) {
            addCriterion("power <>", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThan(String value) {
            addCriterion("power >", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThanOrEqualTo(String value) {
            addCriterion("power >=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThan(String value) {
            addCriterion("power <", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThanOrEqualTo(String value) {
            addCriterion("power <=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLike(String value) {
            addCriterion("power like", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotLike(String value) {
            addCriterion("power not like", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerIn(List<String> values) {
            addCriterion("power in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotIn(List<String> values) {
            addCriterion("power not in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerBetween(String value1, String value2) {
            addCriterion("power between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotBetween(String value1, String value2) {
            addCriterion("power not between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNull() {
            addCriterion("temperature is null");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNotNull() {
            addCriterion("temperature is not null");
            return (Criteria) this;
        }

        public Criteria andTemperatureEqualTo(String value) {
            addCriterion("temperature =", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotEqualTo(String value) {
            addCriterion("temperature <>", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThan(String value) {
            addCriterion("temperature >", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThanOrEqualTo(String value) {
            addCriterion("temperature >=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThan(String value) {
            addCriterion("temperature <", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThanOrEqualTo(String value) {
            addCriterion("temperature <=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLike(String value) {
            addCriterion("temperature like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotLike(String value) {
            addCriterion("temperature not like", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureIn(List<String> values) {
            addCriterion("temperature in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotIn(List<String> values) {
            addCriterion("temperature not in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureBetween(String value1, String value2) {
            addCriterion("temperature between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotBetween(String value1, String value2) {
            addCriterion("temperature not between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andLngIsNull() {
            addCriterion("lng is null");
            return (Criteria) this;
        }

        public Criteria andLngIsNotNull() {
            addCriterion("lng is not null");
            return (Criteria) this;
        }

        public Criteria andLngEqualTo(String value) {
            addCriterion("lng =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(String value) {
            addCriterion("lng <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(String value) {
            addCriterion("lng >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(String value) {
            addCriterion("lng >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(String value) {
            addCriterion("lng <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(String value) {
            addCriterion("lng <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLike(String value) {
            addCriterion("lng like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotLike(String value) {
            addCriterion("lng not like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<String> values) {
            addCriterion("lng in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<String> values) {
            addCriterion("lng not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(String value1, String value2) {
            addCriterion("lng between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(String value1, String value2) {
            addCriterion("lng not between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(String value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(String value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(String value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(String value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(String value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(String value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLike(String value) {
            addCriterion("lat like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotLike(String value) {
            addCriterion("lat not like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<String> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<String> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(String value1, String value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(String value1, String value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andNecStatusIsNull() {
            addCriterion("nec_status is null");
            return (Criteria) this;
        }

        public Criteria andNecStatusIsNotNull() {
            addCriterion("nec_status is not null");
            return (Criteria) this;
        }

        public Criteria andNecStatusEqualTo(String value) {
            addCriterion("nec_status =", value, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusNotEqualTo(String value) {
            addCriterion("nec_status <>", value, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusGreaterThan(String value) {
            addCriterion("nec_status >", value, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusGreaterThanOrEqualTo(String value) {
            addCriterion("nec_status >=", value, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusLessThan(String value) {
            addCriterion("nec_status <", value, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusLessThanOrEqualTo(String value) {
            addCriterion("nec_status <=", value, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusLike(String value) {
            addCriterion("nec_status like", value, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusNotLike(String value) {
            addCriterion("nec_status not like", value, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusIn(List<String> values) {
            addCriterion("nec_status in", values, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusNotIn(List<String> values) {
            addCriterion("nec_status not in", values, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusBetween(String value1, String value2) {
            addCriterion("nec_status between", value1, value2, "necStatus");
            return (Criteria) this;
        }

        public Criteria andNecStatusNotBetween(String value1, String value2) {
            addCriterion("nec_status not between", value1, value2, "necStatus");
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