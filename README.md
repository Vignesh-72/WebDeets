<div align="center">

# 🌐 WebDeets
**A Comprehensive Desktop Network Diagnostic & Web Analysis Utility**

[![SourceForge Downloads](https://img.shields.io/sourceforge/dt/webdeets?style=for-the-badge&logo=sourceforge&logoColor=white&color=EE6600)](https://sourceforge.net/projects/webdeets/)
[![Java Version](https://img.shields.io/badge/Java-19.0.1-007396?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue?style=for-the-badge)](LICENSE)

<br />

**WebDeets** is a lightweight, all-in-one network analysis and diagnostic desktop suite engineered in Java. It consolidates foundational system telemetry commands (`netstat`, customized ICMP `ping` suites) with advanced protocol-level inspection tools (DNS resolution, port scanning, WHOIS lookups, and raw HTTP header/cookie inspection) into an intuitive, real-time GUI terminal.

</div>

---

## 📑 Table of Contents

- [Key Features](#-key-features)
- [Downloads & Releases](#-downloads--releases)
- [System Requirements & Build Info](#-system-requirements--build-info)
- [Detailed Module Walkthrough](#-detailed-module-walkthrough)
  - [1. PC-Info (`netstat` Suite)](#1-pc-info-netstat-suite)
  - [2. ICMP Pinging Utilities](#2-icmp-pinging-utilities)
  - [3. Advanced Pinging & DNS Tools](#3-advanced-pinging--dns-tools)
  - [4. Advanced Web & Security Tools](#4-advanced-web--security-tools)
- [Technical Architecture](#-technical-architecture)
- [Author & Contact](#-author--contact)

---

## ✨ Key Features

- **Local Network Telemetry:** Execute granular `netstat` commands with GUI toggles to view active sockets, process IDs, routing tables, and listening ports.
- **Configurable ICMP Ping Engine:** Execute customized ICMP packet requests specifying packet buffer sizes, TTL, hop limits, and timestamp configurations.
- **Bidirectional DNS Resolvers:** Rapidly map domain hostnames to IP addresses and reverse lookup IP addresses to hostnames.
- **Web Inspection Engine:** Fetch raw webpage content, extract session cookies, and parse remote HTTP/HTTPS response headers directly within the client.
- **Integrated Port Scanner:** Scan endpoints for open services and listening ports to assess network attack surfaces.
- **Live Output Stream:** Integrated terminal panel with visual status indicators (`Loading`, `Completed`, or standard error logs).

---

## 📥 Downloads & Releases

Pre-compiled binary releases and source bundles can be downloaded directly from SourceForge:

| Release Channel | Status | Download Link |
| :--- | :--- | :--- |
| **v1.0 (Stable)** | `Recommended` | [Download WebDeets v1.0](https://sourceforge.net/projects/webdeets) |
| **Beta Channel** | `Pre-release` | [Browse Beta Files](https://sourceforge.net/projects/webdeets/files/WebDeets) |

> **Note:** Version 1.0 contains stability enhancements, resolved edge-case exceptions, and full library optimizations over the beta release.

---

## 💻 System Requirements & Build Info

- **Runtime Environment:** Java Runtime Environment (JRE) / Java Development Kit (JDK) 19+
- **Core Platform:** Java SE 19.0.1 (build `19.0.1+10-21`)
- **Networking Library:** [Apache Commons Net 3.9.0](https://commons.apache.org/proper/commons-net/)
- **Documentation:** Built with standard JavaDoc 19.0.1

---

## 🖥️ Detailed Module Walkthrough

Upon launching WebDeets, the main launcher gives you access to three dedicated operational environments:

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211330711-edc32d5f-6b0c-49e1-97e0-9775c5e02581.png" alt="WebDeets Startup Screen" width="700" />
</div>

---

### 1. PC-Info (`netstat` Suite)

The **PC-Info** workspace wraps system-level network connection auditing into selectable toggles, avoiding manual terminal flag syntax.

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211343628-92b4eb48-7048-435f-9fb4-ebcbcbb26bdc.png" alt="PC-Info Dashboard" width="700" />
</div>

#### Capabilities:
- List all active TCP/UDP connections and listening ports.
- Identify the executable (`.exe` or binary) associated with each open socket.
- Output foreign/local addresses in numerical format without resolving names.
- Display the unique Process Identifier (PID) owning each socket.
- Query and dump the local IP routing table.

#### Workflow & Output Console:
1. Select the desired diagnostic parameter (e.g., *Display All Connections and Ports*).
2. Click **Start**. The console displays an inline `Loading` status while the system socket scan runs:

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211344466-8bbd2467-fe40-471f-ad8c-a0cea132b073.png" alt="Process Running" width="700" />
</div>

3. Upon completion, stdout logs are streamed directly into the embedded output window:

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211345344-7259e76f-6a4b-4260-80b0-a464e153a442.png" alt="Execution Success" width="700" />
</div>

*(If an exception or command failure occurs, error indicators and system traces will populate in the bottom indicator area.)*

---

### 2. ICMP Pinging Utilities

The pinging dashboard offers rapid connectivity and latency validation against remote hosts or IP addresses.

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211347988-04584c63-dc66-41e5-8ee3-e726f5fe9898.png" alt="Pinging Control Center" width="700" />
</div>

#### Configurable Options:
- Standard target reachability verification.
- **Echo Request Count:** Custom transmission iterations (e.g., `ping -n 10`).
- **Buffer Size Tuning:** Custom packet payload sizing (`ping -l 32` / `ping -l 64`).
- **Timestamp Logging:** Record transit timestamps on echo requests (`ping -s 4`).
- **Hop Count / Route Recording:** Track route traversal up to 9 hops (`ping -r 9`).
- **TTL Adjustments:** Manually specify Time-To-Live parameters (`ping -i 10`).

To run an audit, provide the hostname or IP in the top-right field, select your flags, and click **Start**:

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211349004-dcf28c01-dee3-4ab2-b0cf-8392e0b21e84.png" alt="Ping In Action" width="700" />
</div>

---

### 3. Advanced Pinging & DNS Tools

Accessible via the **Advance-Tools** button inside the Pinging view:

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211349855-ec30e20f-7b36-42c1-aee7-9a039a7aec0b.png" alt="Pinging Advanced Tools" width="700" />
</div>

#### Core Capabilities:
- **Host-to-IP Translation:** Convert domain URLs into resolved IPv4 addresses.
- **IP-to-Host Translation:** Perform reverse PTR lookups to discover domain records.
- **Deets-Scan:** Batch execute a suite of ICMP diagnostic probes in a single click.

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211349890-31c09f92-41a5-4a84-9152-dbca16d5a213.png" alt="Enter Host Domain" width="600" />
  <br /><br />
  <img src="https://user-images.githubusercontent.com/107568580/211349930-9e57c319-b6dc-4f28-8c10-3e9818e3de91.png" alt="Host to IP Output" width="700" />
</div>

---

### 4. Advanced Web & Security Tools

Built for webmasters, penetration testers, and sysadmins needing to inspect application-layer responses without launching a full browser.

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211349973-034e535b-4fa4-4dc2-972c-efe572071157.png" alt="Advanced Web Inspection Suite" width="700" />
</div>

#### Included Utilities:
- **WHOIS Queries:** Fetch registry and ownership metadata for registered domains.
- **Multi-IP Enumeration:** Retrieve round-robin DNS records and multi-homed addresses.
- **Raw Web Content Reader:** Fetch remote HTML/source payloads.
- **Port Scanner:** Probe target hosts across standard port ranges to identify running services.
- **Cookie Harvester:** Inspect session cookies and security flags (`HttpOnly`, `Secure`).
- **HTTP Header Inspection:** Read response headers (`Content-Type`, `Server`, cache directives).

<div align="center">
  <img src="https://user-images.githubusercontent.com/107568580/211350037-daa2efca-a735-4469-8003-1b48bab3446c.png" alt="Web Content Inspection Output" width="700" />
</div>

---

## 🛠️ Technical Architecture

WebDeets serves as a centralized administrative GUI wrapping native platform binaries and Java socket utilities:
1. **System Bridge:** Direct runtime process spawning for native low-overhead commands (`netstat`, platform-specific ping binaries).
2. **Network Layer:** Leverages `java.net` alongside Apache Commons Net (`commons-net-3.9.0`) for standard protocol handling (WHOIS client queries, raw HTTP/HTTPS header extraction, and socket connections for port testing).
3. **Threading Model:** Background worker threads decouple command execution from the UI thread, preventing frozen GUI states during prolonged timeouts or long scans.

---

## 👤 Author & Contact

- **Vignesh S.** — [@Vicky](https://www.instagram.com/vicky__v72/)
