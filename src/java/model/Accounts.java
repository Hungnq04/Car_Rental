/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author chang
 */
public class Accounts {
    private int UserID;
    private String Username;
    private String Password;
    private String Phone;
    private String Email;
    private int RoleID;
    private String RoleName;

    public Accounts() {
    }

    public Accounts(int UserID, String Username, String Password, String Phone, String Email, int RoleID, String RoleName) {
        this.UserID = UserID;
        this.Username = Username;
        this.Password = Password;
        this.Phone = Phone;
        this.Email = Email;
        this.RoleID = RoleID;
        this.RoleName = RoleName;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    
}
