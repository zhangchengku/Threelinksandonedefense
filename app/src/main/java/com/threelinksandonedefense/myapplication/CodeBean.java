package com.threelinksandonedefense.myapplication;

import java.util.List;

public class CodeBean {

    /**
     * STATE : 1
     * DATA : [{"Post":"445222"}]
     */

    private String STATE;
    private List<DATABean> DATA;

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * Post : 445222
         */

        private String Post;

        public String getPost() {
            return Post;
        }

        public void setPost(String Post) {
            this.Post = Post;
        }
    }
}
