package com.byteknowledge.mytiles.model;

public class User extends AbstractUUIDEntity {
    
    private static final long serialVersionUID = 6040581995653117204L;

    private String userName;
    private String firstName;
    private String lastName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (userName == null) {
            if (other.getUserName() != null)
                return false;
        } else if (!userName.equals(other.getUserName()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }


}
