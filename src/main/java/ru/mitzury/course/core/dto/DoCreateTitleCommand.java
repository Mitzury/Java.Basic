package ru.mitzury.course.core.dto;

public class DoCreateTitleCommand {

    private String file;
    private String schoolName;
    private String address;
    private String phone;
    private String site;
    private String email;
    private String approvedDate;
    private String approvedOrder;
    private String city;
    private String year;

    public DoCreateTitleCommand() {
        // Jackson
    }

    // ===== setters =====

    public void setFile(String file) {
        this.file = file;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public void setApprovedOrder(String approvedOrder) {
        this.approvedOrder = approvedOrder;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setYear(String year) {
        this.year = year;
    }

    // ===== getters =====

    public String getFile() {
        return file;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getSite() {
        return site;
    }

    public String getEmail() {
        return email;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public String getApprovedOrder() {
        return approvedOrder;
    }

    public String getCity() {
        return city;
    }

    public String getYear() {
        return year;
    }

    // ===== validation =====

    public void validate() {
        if (file == null || file.isBlank()) {
            throw new IllegalArgumentException("file must not be empty");
        }
        if (schoolName == null || schoolName.isBlank()) {
            throw new IllegalArgumentException("schoolName must not be empty");
        }
    }
}
