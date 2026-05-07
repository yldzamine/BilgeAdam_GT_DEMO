FROM mcr.microsoft.com/devcontainers/java:1-21-bookworm

ARG DEBIAN_FRONTEND=noninteractive

RUN rm -f /etc/apt/sources.list.d/yarn.list \
    && apt-get update \
    && apt-get install -y --no-install-recommends \
      ca-certificates \
      curl \
      dbus-x11 \
      fluxbox \
      fonts-liberation \
      libasound2 \
      libatk-bridge2.0-0 \
      libatk1.0-0 \
      libatspi2.0-0 \
      libgbm1 \
      libgtk-3-0 \
      libnss3 \
      libxkbcommon0 \
      libxss1 \
      maven \
      novnc \
      unzip \
      websockify \
      x11vnc \
      xvfb \
      chromium \
      chromium-driver \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*
