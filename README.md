# Token Analyzer

> A Java Swing desktop application for analyzing, decoding, encoding, and brute-forcing **JWT** and **Flask Session Tokens**.

[한국어](README.ko.md)

---

## Features

- **Token Identifier** — automatically detects whether a token is a JWT, Flask Session Token, or unknown
- **JWT (JSON Web Token)**
  - Decode header and payload
  - Encode a new token with custom header, payload, and secret
  - Brute-force the signing secret using a wordlist file
- **Flask Session Token**
  - Decode payload and timestamp
  - Encode a new token with custom payload and secret
  - Brute-force the signing secret using a wordlist file

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| UI Framework | Java Swing |
| UI Theme | [FlatLaf](https://github.com/JFormDesigner/FlatLaf) 3.6 |
| Cryptography | HMAC-SHA256 (javax.crypto), Base64 URL encoding |
| Concurrency | Parallel stream for brute-force |

## How to Run

Download the latest release from the [Releases page](https://github.com/jonber2185/TokenAnalyzer/releases).

### Option 1 — Windows EXE (ZIP)

1. Download `TokenAnalyzer.zip` from the release.
2. **Extract the entire ZIP** — do not move the `.exe` file alone, it requires the bundled runtime folder to run.
3. Run `TokenAnalyzer.exe` inside the extracted folder.

### Option 2 — JAR (cross-platform, requires Java)

```bash
java -jar TokenAnalyzer.jar
```

### Option 3 — From source (Eclipse)

1. Import the project into Eclipse as an existing Java project.
2. Ensure `lib/flatlaf-3.6.jar` is on the build path.
3. Run `Main.java`.

## Usage

### Identifier Tab

Paste any token into the input field and click **Identify**. The app will detect the token type and display the result.

### JWT Tab

| Tab | Description |
|---|---|
| Decode | Paste a JWT to decode its header and payload |
| Encode | Enter header JSON, payload JSON, and a secret to generate a new JWT |
| Brute Force | Provide a JWT and a wordlist file path to search for the signing secret |

### Flask Session Token Tab

| Tab | Description |
|---|---|
| Decode | Paste a Flask session token to decode its payload and creation timestamp |
| Encode | Enter payload JSON and a secret to generate a new Flask session token |
| Brute Force | Provide a token and a wordlist file path to search for the signing secret |

## Token Formats

**JWT:** `<Base64URL(header)>.<Base64URL(payload)>.<HMAC-SHA256 signature>`

**Flask Session Token:** `<Base64URL(payload)>.<Base64URL(timestamp)>.<HMAC-SHA256 signature>`

## Note
- This tool is intended for **authorized security testing and educational use only**.
