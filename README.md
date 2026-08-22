# Aurelia Hospital Management System

A desktop-based Hospital Management System built with Java Swing and MySQL, providing role-based dashboards for Admin, Manager, Doctor, Reception, Pharmacist, and Theater staff.

## Structure

```
src/lk/aurelia/
├── connection/        # Database connection helper (MySQL JDBC)
├── model/             # Data models (e.g., userDetails)
├── Validation/        # Input validation utilities
├── Gui/               # Windows & dashboards (Splash, SignIn, role dashboards)
├── Panel/             # Feature panels loaded into dashboards
│   ├── ManageDoctorsPanel
│   ├── ManageNursesPanel
│   ├── ManagePharmacistPanel
│   ├── ManageReception
│   ├── ManageTheaterPanel
│   ├── PharmacistGrn / Stock / Invoice panels
│   └── Reception & Theater patient/schedule panels
├── Dialog/            # Detail view dialogs (view more details per role)
└── component/         # Reusable custom Swing components
    └── table/         # Custom table renderers and helpers
```

## Features

- Splash screen and secure sign-in with role-based access
- Dedicated dashboards for Admin, Manager, Doctor, Reception, Pharmacist, and Theater roles
- Staff management: doctors, nurses, pharmacists, receptionists, theater staff
- Patient registration and patient scheduling (reception & theater)
- Pharmacy operations: stock management, GRN (goods received notes), invoicing
- "View more" dialogs for detailed records
- Report generation via JasperReports and PDF export (iText/OpenPDF)
- Toast notifications for user feedback

## Design

- Modern flat look-and-feel using FlatLaf IntelliJ themes
- Fully custom Swing components: rounded buttons, text fields, password fields, combo boxes, text areas, and backgrounds
- Custom-styled tables with hover effects, custom headers, boolean and multi-line cell renderers
- Custom scroll bars and animated UI transitions powered by the Timing Framework
- Consistent visual language across all role-based dashboards

## Technologies

- **Java** (Swing / AWT) – core application framework
- **MySQL** + **MySQL Connector/J** – relational database persistence
- **NetBeans IDE** – project structure and GUI building (Ant build system)
- **FlatLaf** – modern flat UI themes (`flatlaf`, `flatlaf-extras`, `flatlaf-intellij-themes`)
- **Raven** – window/title bar styling and modern UI effects (`raven-8.0.0.jar`)
- **JCalendar** – date picker components
- **JasperReports** – report generation
- **iText / OpenPDF** – PDF document export
- **swing-toast-notifications** – toast notification popups
- **TimingFramework** – animation support
