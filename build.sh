#!/usr/bin/env bash
set -euo pipefail

if ! command -v mvn &>/dev/null; then
    echo "[ERROR] Maven not found. Please install Maven and add it to PATH."
    exit 1
fi

echo "[INFO] Cleaning previous build..."
mvn clean

echo "[INFO] Building CreeperGuard..."
mvn package -DskipTests

echo
echo "[OK] Build complete!"
ls -1 target/CreeperGuard-*.jar 2>/dev/null | grep -v original | while read -r f; do
    echo "[OK] Artifact: $f"
done
