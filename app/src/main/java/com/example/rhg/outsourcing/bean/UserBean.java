package com.example.rhg.outsourcing.bean;

import java.util.List;

/**
 *desc:
 *author：remember 
 *time：2016/6/17 14:48
 *email：1013773046@qq.com
 */
public class UserBean {

    /**
     * total : 1
     * rows : [{"ID":"1","CName":"19216801","PersonId":"330424","Phonenumber":"1860000000","Pwd":"123","Pic":"http://zousitanghulu.com/Pic/ClientPic/19216801.jpg"}]
     */

    private int total;
    /**
     * ID : 1
     * CName : 19216801
     * PersonId : 330424
     * Phonenumber : 1860000000
     * Pwd : 123
     * Pic : http://zousitanghulu.com/Pic/ClientPic/19216801.jpg
     */

    private List<User> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getRows() {
        return rows;
    }

    public void setRows(List<User> rows) {
        this.rows = rows;
    }

    public static class User {
        private String ID;
        private String CName;
        private String PersonId;
        private String Phonenumber;
        private String Pwd;
        private String Pic;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCName() {
            return CName;
        }

        public void setCName(String CName) {
            this.CName = CName;
        }

        public String getPersonId() {
            return PersonId;
        }

        public void setPersonId(String PersonId) {
            this.PersonId = PersonId;
        }

        public String getPhonenumber() {
            return Phonenumber;
        }

        public void setPhonenumber(String Phonenumber) {
            this.Phonenumber = Phonenumber;
        }

        public String getPwd() {
            return Pwd;
        }

        public void setPwd(String Pwd) {
            this.Pwd = Pwd;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }
    }
}
