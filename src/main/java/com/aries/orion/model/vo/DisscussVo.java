package com.aries.orion.model.vo;

public class DisscussVo {
    private Long id;
    private Long gaeaid;
    private String username;
    private String image;
    private String theme;
    private String content;
    private Boolean anonymousSend;
    private Boolean anonymousReply;
    private Long categoryId;
    private String updateTime;
    private String insertTime;
    private Integer replyNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGaeaid() {
        return gaeaid;
    }

    public void setGaeaid(Long gaeaid) {
        this.gaeaid = gaeaid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAnonymousSend() {
        return anonymousSend;
    }

    public void setAnonymousSend(Boolean anonymousSend) {
        this.anonymousSend = anonymousSend;
    }

    public Boolean getAnonymousReply() {
        return anonymousReply;
    }

    public void setAnonymousReply(Boolean anonymousReply) {
        this.anonymousReply = anonymousReply;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public static final class DisscussVoBuilder {
        private Long id;
        private Long gaeaid;
        private String username;
        private String image;
        private String theme;
        private String title;
        private Boolean anonymousSend;
        private Boolean anonymousReply;
        private Long categoryId;
        private String updateTime;
        private String insertTime;
        private Integer replyNum;

        private DisscussVoBuilder() {
        }

        public static DisscussVoBuilder aDisscussVo() {
            return new DisscussVoBuilder();
        }

        public DisscussVoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DisscussVoBuilder gaeaid(Long gaeaid) {
            this.gaeaid = gaeaid;
            return this;
        }

        public DisscussVoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public DisscussVoBuilder image(String image) {
            this.image = image;
            return this;
        }

        public DisscussVoBuilder theme(String theme) {
            this.theme = theme;
            return this;
        }

        public DisscussVoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public DisscussVoBuilder anonymousSend(Boolean anonymousSend) {
            this.anonymousSend = anonymousSend;
            return this;
        }

        public DisscussVoBuilder anonymousReply(Boolean anonymousReply) {
            this.anonymousReply = anonymousReply;
            return this;
        }

        public DisscussVoBuilder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public DisscussVoBuilder updateTime(String updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public DisscussVoBuilder insertTime(String insertTime) {
            this.insertTime = insertTime;
            return this;
        }

        public DisscussVoBuilder replyNum(Integer replyNum) {
            this.replyNum = replyNum;
            return this;
        }

        public DisscussVo build() {
            DisscussVo disscussVo = new DisscussVo();
            disscussVo.setId(id);
            disscussVo.setGaeaid(gaeaid);
            disscussVo.setUsername(username);
            disscussVo.setImage(image);
            disscussVo.setTheme(theme);
            disscussVo.setContent(title);
            disscussVo.setAnonymousSend(anonymousSend);
            disscussVo.setAnonymousReply(anonymousReply);
            disscussVo.setCategoryId(categoryId);
            disscussVo.setUpdateTime(updateTime);
            disscussVo.setInsertTime(insertTime);
            disscussVo.setReplyNum(replyNum);
            return disscussVo;
        }
    }
}
