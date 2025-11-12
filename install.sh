#!/usr/bin/env bash
set -e

# === CONFIGURATION ===
SRC_DIR="src"
OUT_DIR="build/out"
LIB_DIR="build/lib"
MAIN_CLASS="typing.core.Game"   # change this if your entry point changes
JAR_NAME="typinggame.jar"

# === PREPARE OUTPUT DIRECTORY ===
mkdir -p "$OUT_DIR"

# === COMPILE ALL JAVA FILES ===
echo "Compiling Java sources..."
find "$SRC_DIR" -name "*.java" > sources.txt

javac -cp "$LIB_DIR/lanterna-3.1.3.jar" -d "$OUT_DIR" @sources.txt
echo "Compilation complete."

# === PACKAGE INTO JAR (optional) ===
echo "Creating runnable JAR..."
jar --create --file="$OUT_DIR/$JAR_NAME" \
    --main-class="$MAIN_CLASS" \
    -C "$OUT_DIR" .

# === HOW TO RUN ===
echo
echo "Run with:"
echo "  java -cp \"$OUT_DIR:$LIB_DIR/lanterna-3.1.3.jar\" $MAIN_CLASS"
echo "or just:"
echo "  java -jar \"$OUT_DIR/$JAR_NAME\""

