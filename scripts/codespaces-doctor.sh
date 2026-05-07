#!/usr/bin/env bash
set -euo pipefail

QUICK=false
if [[ "${1:-}" == "--quick" ]]; then
  QUICK=true
fi

fail() {
  echo "[FAIL] $1"
  exit 1
}

ok() {
  echo "[ OK ] $1"
}

require_command() {
  local command_name="$1"
  command -v "$command_name" >/dev/null 2>&1 || fail "$command_name is not installed"
  ok "$command_name is available"
}

require_command java
require_command mvn
require_command chromium
require_command chromedriver
require_command Xvfb
require_command x11vnc
require_command websockify
require_command fluxbox

java -version 2>&1 | grep 'version "21' >/dev/null \
  && ok "Java 21 is active" \
  || fail "Java 21 must be active for this training project"

chromium --headless=new --no-sandbox --disable-dev-shm-usage --disable-gpu --dump-dom about:blank >/tmp/gt-demo-chromium-check.html \
  && ok "Chromium can start in headless mode" \
  || fail "Chromium could not start; rebuild the dev container"

if [[ "$QUICK" == "false" ]]; then
  mvn -q -DskipTests test \
    && ok "Maven test compilation succeeded" \
    || fail "Maven test compilation failed"
fi

echo "Codespaces training environment looks ready."
