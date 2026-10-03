# Aurelia Hospital Management System

A Java Swing desktop application for managing hospital operations — staff, patients,
appointments, pharmacy stock, invoicing, and operating-theater workflows — built with
NetBeans, backed by MySQL, and styled with FlatLaf.

## Project Structure

```
AureliaHospital/
├── src/lk/aurelia/
│   ├── Gui/            # Main windows: Splash, SignIn & role dashboards (JFrames)
│   ├── Panel/          # Feature panels swapped into each dashboard
│   ├── Dialog/         # "View more" detail dialogs
│   ├── component/      # Custom Swing widgets (rounded fields, buttons, tables)
│   │   └── table/      # Custom table renderers & scroll UI
│   ├── connection/     # MySQL JDBC helper
│   ├── model/          # POJOs (e.g. logged-in user session)
│   ├── Validation/     # Regex-based input validators
│   ├── reports/        # Compiled JasperReports (.jasper)
│   └── img/            # Images, logos & icons
├── db/                 # init.sql — schema + redacted demo data for `aurelia_db`
├── lib/                # Third-party JARs (MySQL driver, FlatLaf, JasperReports, ...)
├── nbproject/          # NetBeans project configuration (Ant)
├── test/               # Test sources
└── build.xml           # Apache Ant build script → dist/AureliaHospital.jar
```

**Entry point:** `lk.aurelia.Gui.Splash` (animated splash screen → sign-in)

## Features

- **Animated splash screen** with database-connection progress leading to a login screen
- **Role-based access control** — six dedicated dashboards launched on sign-in:
  Admin, Doctor, Reception, Manager, Pharmacist, and Theater
- **Staff management (CRUD)** — doctors, nurses, pharmacists, receptionists, and
  operating-theater staff, each with detail dialogs and printable reports
- **Reception module** — patient registration and appointment scheduling
- **Pharmacy module** — stock management, GRN (goods received notes), and customer
  invoicing
- **Theater module** — operating-theater management and theater patient handling with
  printable patient slips
- **Report generation** — 9 JasperReports templates (staff details, schedules, invoices,
  theater slips) viewable via an embedded JasperViewer
- **Input validation** — email, Sri Lankan mobile/landline numbers, NIC, password
  strength, and date-time formats
- **Polished UX** — toast notifications, hover effects, custom scroll bars, and rounded
  form controls

## Design

- **Layered architecture:** `Gui` windows host swappable `Panel` components that contain
  view + controller logic; a lightweight `model.userDetails` POJO carries the signed-in
  user between screens; all persistence flows through `connection.MySQL`
- **Static singleton access:** a single shared JDBC `Connection` managed by the static
  `MySQL` helper exposing `search()` / `iud()` methods; each dashboard exposes itself as
  a static singleton instance
- **Enum-strategy validation:** `Validation` enum maps each field type to a compiled
  regex (`EMAIL_VALIDATION`, NIC, phone, password strength, datetime)
- **Manual panel switching:** dashboards swap content panels at runtime instead of using
  `CardLayout`
- **Reusable component library:** hand-rolled rounded widgets (`RoundButton`,
  `RoundTextField`, ...) and pluggable table renderers keep the UI consistent
- **FlatLaf theming** with SVG icon support for a modern flat look across all screens

## Technologies

| Category | Technology |
|---|---|
| Language | Java SE 8 |
| Build | Apache Ant (NetBeans project) |
| UI | Java Swing (NetBeans GUI Builder `.form` files) |
| Look & feel | FlatLaf 3.x (+ IntelliJ themes, FlatSVGIcon/jsvg) |
| Database | MySQL (JDBC — `mysql-connector-java-8.0.24`) |
| Reporting | JasperReports 6.x (+ iText/OpenPDF for PDF output) |
| Date picking | JCalendar (`JDateChooser`) |
| Notifications | Raven swing-toast-notifications |
| Animation | TimingFramework |
| Packaging | launch4j (Windows launcher) |

## Getting Started

### Prerequisites

- JDK 8+
- NetBeans (Ant-based Java SE project support)
- MySQL Server
  (connection settings are configured in `src/lk/aurelia/connection/MySQL.java`)

### Database setup

Import the schema and demo data:

```sh
mysql -u root -p < db/init.sql
```

This creates the `aurelia_db` database with all 22 tables, including staff accounts for each
role, patients, channeling, theater operations, pharmacy stock, and invoices.

> **Note:** the seed data in `db/init.sql` is **anonymised** — all names, emails, phone
> numbers, addresses, passwords, and the admin passkey are placeholders
> (`user1@example.com` / `password`, etc.) rather than real records. Every demo account
> shares the password `password`.

### Build & Run

1. Open the project in NetBeans and run it (`F6`), or build from the CLI:

   ```sh
   ant clean jar
   java -jar dist/AureliaHospital.jar
   ```
