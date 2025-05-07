# 🦆 DuckVest — Investment Brokerage Platform

**DuckVest** is a backend simulation of an investment brokerage system built with Java and Spring Boot. It models essential features of a stock trading platform: users, stocks, exchanges, balances, and scheduled trading events.

---
## 🐣 Why DuckVest?

Because a wise duck always invests smart. Be like the duck.

---

## 🚀 Features

- 📈 Manage stocks ~and simulate stock price updates~
- 🧑 Manage users and their account balances
- 🏛️ Simulate multiple stock exchanges
- 💸 Buy and sell stocks ~with automatic balance updates~
- ⏱️ Scheduled tasks for periodic operations
- 📡 REST API for all operations
- 🛠️ Modular, layered architecture

---

## 🧱 Tech Stack

- **Java 24**
- **Spring Boot 3**
- **Spring Security**
- **PostgreSQL** for persistent storage
- **ModelMapper**
---

## ⚙️ Getting Started

> Requirements: Java 8+, PostgreSQL, Docker (optional)

1. Clone the repository:

```bash
git clone https://github.com/your-name/duckvest.git
cd duckvest
```

---
## 🧠 Architecture Overview
```text
[Controller] → [Service] → [Repository] → [PostgreSQL]
                     ↓
               [Scheduler Task]
                     ↓
               [Stock Exchange]
```
## 📌 Possible Extensions

    ✅ Real-time stock price integration (e.g., Alpha Vantage, Yahoo Finance)

    🧾 Transaction history and reporting

    📬 Email or notification system

    💳 Banking simulation and fund transfers

    📱 Frontend interface (Web or Mobile)

## ✍️ Author

Made with passion and caffeine.
If you like the project — give it a ⭐!
