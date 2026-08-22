# Aurelia Hospital Management System

A role-based hospital management desktop application built with Java Swing and MySQL. It streamlines the day-to-day operations of a hospital across administration, medical staff, pharmacy, reception, and surgical theater departments.

## Features

- **Splash Screen & Authentication** — animated splash screen on launch followed by secure sign-in.
- **Role-Based Dashboards** — dedicated dashboards for Admin, Manager, Doctor, Pharmacist, Reception, and Theater users.
- **Staff Management** — manage doctors, nurses, pharmacists, receptionists, and theater staff with detailed profile views.
- **Patient Registration & Scheduling** — register patients and book/manage appointment schedules at reception.
- **Pharmacy Module** — stock management, Goods Received Notes (GRN), and patient invoicing/billing.
- **Theater Module** — manage surgical theater patients and issue procedure slips.
- **Report Generation** — printable JasperReports for doctor/nurse/pharmacist details, schedules, invoices, patient lists, and theater slips.
- **Input Validation** — dedicated validation layer for form inputs.

## Design

- **Modern FlatLaf look-and-feel** with light theme and IntelliJ themes support.
- **Custom UI kit** — rounded buttons, text fields, password fields, combo boxes, text areas, hover effects, and custom scrollbars (`lk.aurelia.component`).
- **Custom tables** — styled table headers, boolean/text-area cell renderers, row hover highlighting, and custom scroll buttons (`lk.aurelia.component.table`).
- **Toast notifications** for non-intrusive user feedback.
- **NetBeans GUI Builder (Matisse)** forms (`.form` files) paired with every window, dialog, and panel class.
- **Splash → Sign In → Dashboard flow** routing each user to their role-specific workspace.

## Structure

```
src/lk/aurelia/
├── Gui/          # Main windows: Splash, SignIn & 6 role dashboards
├── Panel/        # Embedded content panels (management, pharmacy,
│                 #   reception, theater modules)
├── Dialog/       # "View more" detail dialogs & patient selector
├── component/    # Custom Swing widgets
│   └── table/    # Custom table renderers & helpers
├── connection/   # MySQL JDBC connection singleton
├── model/        # Data models (user details)
├── Validation/   # Input validation utilities
├── reports/      # Compiled JasperReports (.jasper)
└── img/          # Images: logos, icons, role photos
```

Main entry point: `lk.aurelia.Gui.Splash`

## Technologies

| Technology | Purpose |
|---|---|
| Java 8 | Core application language |
| Swing (NetBeans GUI Builder) | Desktop UI |
| FlatLaf 3.1.1 (+ themes/extras) | Modern look-and-feel |
| MySQL + Connector/J 8.0.24 | Database & JDBC connectivity |
| JasperReports 6.21.3 | Report generation (PDF/print) |
| iText / OpenPDF | PDF document support |
| jCalendar 1.4 | Date picker components |
| swing-toast-notifications 1.0.3 | Toast notifications |
| Raven 8.0.0 | Blur effects library |
| TimingFramework | Animation timing |
| jSVG 1.4.0 | SVG rendering |
| Launch4j | Windows EXE wrapper |

## Getting Started

### Prerequisites

- JDK 8 or later
- MySQL Server with the `aurelia_db` database (default connection: `jdbc:mysql://localhost:3306/aurelia_db`, user `root`)
- NetBeans (recommended, for opening the project)

### Run

Open the project in NetBeans and run it, or from the command line:

```bash
java -jar dist/AureliaHospital.jar
```
