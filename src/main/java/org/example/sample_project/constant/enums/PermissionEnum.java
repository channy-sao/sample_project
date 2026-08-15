package org.example.sample_project.constant.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionEnum {

  // ===== PERFORMANCE =====
  PERFORMANCE_READ("PERFORMANCE_READ", "Read performance appraisals and goals"),
  PERFORMANCE_CREATE("PERFORMANCE_CREATE", "Create new performance appraisal cycles"),
  PERFORMANCE_UPDATE("PERFORMANCE_UPDATE", "Update performance scores and feedback"),
  PERFORMANCE_DELETE("PERFORMANCE_DELETE", "Delete performance records"),

  // ===== RECRUITMENT =====
  RECRUITMENT_READ("RECRUITMENT_READ", "Read job openings and applications"),
  RECRUITMENT_CREATE("RECRUITMENT_CREATE", "Post new job openings or candidates"),
  RECRUITMENT_UPDATE("RECRUITMENT_UPDATE", "Update applicant status and interview pipelines"),
  RECRUITMENT_DELETE("RECRUITMENT_DELETE", "Delete job posts or applications"),

  // ===== ONBOARDING =====
  ONBOARDING_READ("ONBOARDING_READ", "Read onboarding and offboarding tasks"),
  ONBOARDING_CREATE("ONBOARDING_CREATE", "Create onboarding checklists"),
  ONBOARDING_UPDATE("ONBOARDING_UPDATE", "Update onboarding progress and task completion"),
  ONBOARDING_DELETE("ONBOARDING_DELETE", "Delete onboarding tasks or plans"),

  // ===== TRAINING =====
  TRAINING_READ("TRAINING_READ", "Read training courses and enrollments"),
  TRAINING_CREATE("TRAINING_CREATE", "Create new training programs"),
  TRAINING_UPDATE("TRAINING_UPDATE", "Update training schedules and completions"),
  TRAINING_DELETE("TRAINING_DELETE", "Delete training records"),

  // ===== BENEFIT =====
  BENEFIT_READ("BENEFIT_READ", "Read employee benefits and insurance plans"),
  BENEFIT_CREATE("BENEFIT_CREATE", "Enroll employees in new benefits plans"),
  BENEFIT_UPDATE("BENEFIT_UPDATE", "Update benefits coverage and configurations"),
  BENEFIT_DELETE("BENEFIT_DELETE", "Remove employees from benefit packages"),

  // ===== SHIFT =====
  SHIFT_READ("SHIFT_READ", "Read work schedules and shift rosters"),
  SHIFT_CREATE("SHIFT_CREATE", "Create new work shifts and rosters"),
  SHIFT_UPDATE("SHIFT_UPDATE", "Update shift rotations and assignments"),
  SHIFT_DELETE("SHIFT_DELETE", "Delete shift records"),

  // ===== ASSET =====
  ASSET_READ("ASSET_READ", "Read company assets assigned to employees"),
  ASSET_CREATE("ASSET_CREATE", "Assign new assets like laptops or phones to employees"),
  ASSET_UPDATE("ASSET_UPDATE", "Update asset status and assignment details"),
  ASSET_DELETE("ASSET_DELETE", "Revoke or delete asset logs"),


  // ===== EMPLOYEE =====
  EMPLOYEE_READ("EMPLOYEE_READ", "Read employee profiles"),
  EMPLOYEE_CREATE("EMPLOYEE_CREATE", "Create new employee records"),
  EMPLOYEE_UPDATE("EMPLOYEE_UPDATE", "Update employee profiles"),
  EMPLOYEE_DELETE("EMPLOYEE_DELETE", "Delete employee records"),

  // ===== DEPARTMENT =====
  DEPARTMENT_READ("DEPARTMENT_READ", "Read department information"),
  DEPARTMENT_CREATE("DEPARTMENT_CREATE", "Create new departments"),
  DEPARTMENT_UPDATE("DEPARTMENT_UPDATE", "Update department information"),
  DEPARTMENT_DELETE("DEPARTMENT_DELETE", "Delete departments"),

  // ===== SALARY / PAYROLL =====
  PAYROLL_READ("PAYROLL_READ", "Read payroll and salary structures"),
  PAYROLL_CREATE("PAYROLL_CREATE", "Process new payroll periods"),
  PAYROLL_UPDATE("PAYROLL_UPDATE", "Update payroll and salary details"),
  PAYROLL_DELETE("PAYROLL_DELETE", "Delete payroll records"),

  // ===== LEAVE / TIME OFF =====
  LEAVE_READ("LEAVE_READ", "Read leave requests and balances"),
  LEAVE_CREATE("LEAVE_CREATE", "Submit new leave requests"),
  LEAVE_UPDATE("LEAVE_UPDATE", "Approve or update leave requests"),
  LEAVE_DELETE("LEAVE_DELETE", "Cancel or delete leave requests"),

  // ===== ATTENDANCE =====
  ATTENDANCE_READ("ATTENDANCE_READ", "Read attendance logs and timesheets"),
  ATTENDANCE_CREATE("ATTENDANCE_CREATE", "Log new attendance entries"),
  ATTENDANCE_UPDATE("ATTENDANCE_UPDATE", "Update attendance and timesheets"),
  ATTENDANCE_DELETE("ATTENDANCE_DELETE", "Delete attendance logs"),

  // ===== CONTRACT =====
  CONTRACT_READ("CONTRACT_READ", "Read employment contracts"),
  CONTRACT_CREATE("CONTRACT_CREATE", "Create new employment contracts"),
  CONTRACT_UPDATE("CONTRACT_UPDATE", "Update employment contracts"),
  CONTRACT_DELETE("CONTRACT_DELETE", "Delete employment contracts"),

  // ===== USER =====
  USER_READ("USER_READ", "Read user information"),
  USER_CREATE("USER_CREATE", "Create new user"),
  USER_UPDATE("USER_UPDATE", "Update user information"),
  USER_DELETE("USER_DELETE", "Delete user"),

  // ===== ROLE =====
  ROLE_READ("ROLE_READ", "Read role information"),
  ROLE_CREATE("ROLE_CREATE", "Create new role"),
  ROLE_UPDATE("ROLE_UPDATE", "Update role information"),
  ROLE_DELETE("ROLE_DELETE", "Delete role"),

  // ===== PRODUCT =====
  PRODUCT_READ("PRODUCT_READ", "Read product information"),
  PRODUCT_CREATE("PRODUCT_CREATE", "Create new product"),
  PRODUCT_UPDATE("PRODUCT_UPDATE", "Update product information"),
  PRODUCT_DELETE("PRODUCT_DELETE", "Delete product"),

  // ===== CATEGORY =====
  CATEGORY_READ("CATEGORY_READ", "Read category information"),
  CATEGORY_CREATE("CATEGORY_CREATE", "Create new category"),
  CATEGORY_UPDATE("CATEGORY_UPDATE", "Update category information"),
  CATEGORY_DELETE("CATEGORY_DELETE", "Delete category"),

  // add news below

  // ===== ORDER =====
  ORDER_CANCEL("ORDER_CANCEL", "Cancel order information"),
  ORDER_CREATE("ORDER_CREATE", "Create new order"),
  ORDER_UPDATE("ORDER_UPDATE", "Update order information"),
  ORDER_DELETE("ORDER_DELETE", "Delete order"),

  // ===== ADDRESS =====
  ADDRESS_READ("ADDRESS_READ", "Read address information"),
  ADDRESS_CREATE("ADDRESS_CREATE", "Create new address information"),
  ADDRESS_UPDATE("ADDRESS_UPDATE", "Update address information"),
  ADDRESS_DELETE("ADDRESS_DELETE", "Delete address information"),

  // ===== REVIEW =====
  REVIEW_READ("REVIEW_READ", "Read review information"),
  REVIEW_CREATE("REVIEW_CREATE", "Create new review information"),
  REVIEW_UPDATE("REVIEW_UPDATE", "Update review information"),
  REVIEW_DELETE("REVIEW_DELETE", "Delete review information"),

  // ===== PROMOTION =====
  PROMOTION_READ("PROMOTION_READ", "Read promotion information"),
  PROMOTION_CREATE("PROMOTION_CREATE", "Create new promotion information"),
  PROMOTION_UPDATE("PROMOTION_UPDATE", "Update promotion information"),
  PROMOTION_DELETE("PROMOTION_DELETE", "Delete promotion information"),

  // ===== COUPON =====
  COUPON_READ("COUPON_READ", "Read coupon information"),
  COUPON_CREATE("COUPON_CREATE", "Create new coupon information"),
  COUPON_UPDATE("COUPON_UPDATE", "Update coupon information"),
  COUPON_DELETE("COUPON_DELETE", "Delete coupon information"),

  // ===== BANNER ======
  BANNER_READ("BANNER_READ", "Read banner information"),
  BANNER_CREATE("BANNER_CREATE", "Create new banner information"),
  BANNER_UPDATE("BANNER_UPDATE", "Update banner information"),
  BANNER_DELETE("BANNER_DELETE", "Delete banner information"),

  // ===== STOCK =====
  STOCK_READ("STOCK_READ", "Read stock information"),
  STOCK_CREATE("STOCK_CREATE", "Create new stock information"),
  STOCK_UPDATE("STOCK_UPDATE", "Update stock information"),
  STOCK_DELETE("STOCK_DELETE", "Delete stock information"),

  // ===== REPORT =====
  REPORT_READ("REPORT_READ", "Read report information"),
  REPORT_CREATE("REPORT_CREATE", "Create new report information"),
  REPORT_UPDATE("REPORT_UPDATE", "Update report information"),
  REPORT_DELETE("REPORT_DELETE", "Delete report information"),

  // ===== DASHBOARD =====
  DASHBOARD_READ("DASHBOARD_READ", "Read dashboard information"),
  DASHBOARD_UPDATE("DASHBOARD_UPDATE", "Update dashboard information"),

  // ===== STOCK_IMPORT =====
  STOCK_IMPORT("STOCK_IMPORT", "Import stock information"),

  // ===== ANALYTICS =====
  ANALYTICS_READ("ANALYTICS_READ", "Read analytics information"),
  ANALYTICS_UPDATE("ANALYTICS_UPDATE", "Update analytics information"),

  // ===== SETTINGS =====
  SETTINGS_READ("SETTINGS_READ", "Read settings information"),
  SETTINGS_UPDATE("SETTINGS_UPDATE", "Update settings information"),

  // ===== NOTIFICATION =====
  NOTIFICATION_READ("NOTIFICATION_READ", "Read notification information"),
  NOTIFICATION_CREATE("NOTIFICATION_CREATE", "Create new notification information"),
  NOTIFICATION_UPDATE("NOTIFICATION_UPDATE", "Update notification information"),
  NOTIFICATION_DELETE("NOTIFICATION_DELETE", "Delete notification information"),

  // ===== MEDIA =====
  MEDIA_READ("MEDIA_READ", "Read media information"),
  MEDIA_CREATE("MEDIA_CREATE", "Create new media information"),
  MEDIA_UPDATE("MEDIA_UPDATE", "Update media information"),
  MEDIA_DELETE("MEDIA_DELETE", "Delete media information"),

  // ===== OTHER =====
  OTHER_READ("OTHER_READ", "Read other information"),
  OTHER_CREATE("OTHER_CREATE", "Create new other information"),
  OTHER_UPDATE("OTHER_UPDATE", "Update other information"),
  OTHER_DELETE("OTHER_DELETE", "Delete other information");

  private final String name;
  private final String description;

  public String getCategory() {
    return this.name().split("_")[0];
  }
}
