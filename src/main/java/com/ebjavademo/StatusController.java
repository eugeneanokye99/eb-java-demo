package com.ebjavademo;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Value("${spring.application.name:eb-java-demo}")
    private String appName;

    @Value("${APP_VERSION:1.0.0}")
    private String appVersion;

    @Value("${EXTERNAL_SERVICE_NAME:None}")
    private String externalServiceName;

    @Value("${S3_PROFILE_BUCKET:unset}")
    private String s3ProfileBucket;

    @Value("${S3_REGION:unset}")
    private String s3Region;

    @Value("${spring.profiles.active:default}")
    private String springProfile;

    @GetMapping(value = "/", produces = "text/html")
    public String root() {
        String now = Instant.now().toString();
        String html = """
            <!doctype html>
            <html lang="en">
            <head>
              <meta charset="utf-8" />
              <meta name="viewport" content="width=device-width, initial-scale=1" />
              <title>{{APP_NAME}}</title>
              <style>
                :root {
                  color-scheme: dark;
                }
                body {
                  margin: 0;
                  font-family: "Segoe UI", Arial, sans-serif;
                  background: radial-gradient(1200px 600px at 10% 10%, #1f3b73, #0c111b 60%);
                  color: #e7eefc;
                }
                .wrap {
                  min-height: 100vh;
                  display: flex;
                  align-items: center;
                  justify-content: center;
                  padding: 32px;
                }
                .card {
                  width: min(900px, 100%);
                  background: rgba(12, 18, 32, 0.9);
                  border: 1px solid rgba(120, 160, 255, 0.25);
                  border-radius: 18px;
                  padding: 28px;
                  box-shadow: 0 24px 80px rgba(0, 0, 0, 0.45);
                  backdrop-filter: blur(6px);
                }
                h1 {
                  margin: 0 0 6px;
                  font-size: 28px;
                  letter-spacing: 0.4px;
                }
                .subtitle {
                  margin: 0 0 18px;
                  color: #b5c7f3;
                }
                .grid {
                  display: grid;
                  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
                  gap: 14px;
                }
                .tile {
                  background: rgba(20, 30, 52, 0.9);
                  border: 1px solid rgba(120, 160, 255, 0.2);
                  border-radius: 12px;
                  padding: 14px 16px;
                }
                .label {
                  text-transform: uppercase;
                  font-size: 11px;
                  letter-spacing: 1px;
                  color: #9cb3e6;
                }
                .value {
                  margin-top: 6px;
                  font-size: 16px;
                  font-weight: 600;
                }
                .pulse {
                  display: inline-flex;
                  align-items: center;
                  gap: 8px;
                }
                .dot {
                  width: 10px;
                  height: 10px;
                  border-radius: 50%;
                  background: #41f28c;
                  box-shadow: 0 0 10px rgba(65, 242, 140, 0.9);
                  animation: pulse 1.6s infinite ease-in-out;
                }
                @keyframes pulse {
                  0%, 100% { transform: scale(0.9); opacity: 0.7; }
                  50% { transform: scale(1.2); opacity: 1; }
                }
                .footer {
                  margin-top: 20px;
                  font-size: 12px;
                  color: #8aa1d1;
                  display: flex;
                  justify-content: space-between;
                  gap: 12px;
                  flex-wrap: wrap;
                }
              </style>
            </head>
            <body>
              <div class="wrap">
                <section class="card">
                  <div class="pulse">
                    <span class="dot"></span>
                    <h1>{{APP_NAME}}</h1>
                  </div>
                  <p class="subtitle">Spring Boot is up and runningggggg.</p>

                  <div class="grid">
                    <div class="tile">
                      <div class="label">Version</div>
                      <div class="value">{{APP_VERSION}}</div>
                    </div>
                    <div class="tile">
                      <div class="label">Profile</div>
                      <div class="value">{{SPRING_PROFILE}}</div>
                    </div>
                    <div class="tile">
                      <div class="label">External Service</div>
                      <div class="value">{{EXTERNAL_SERVICE}}</div>
                    </div>
                    <div class="tile">
                      <div class="label">S3 Bucket</div>
                      <div class="value">{{S3_BUCKET}}</div>
                    </div>
                    <div class="tile">
                      <div class="label">S3 Region</div>
                      <div class="value">{{S3_REGION}}</div>
                    </div>
                    <div class="tile">
                      <div class="label">Server Time</div>
                      <div class="value" id="clock">{{SERVER_TIME}}</div>
                    </div>
                  </div>

                  <div class="footer">
                    <div>Health: <strong>OK</strong></div>
                    <div>Endpoint: <code>/health</code></div>
                  </div>
                </section>
              </div>
              <script>
                const clock = document.getElementById('clock');
                setInterval(() => {
                  clock.textContent = new Date().toISOString();
                }, 1000);
              </script>
            </body>
            </html>
            """;
        return html
            .replace("{{APP_NAME}}", safe(appName))
            .replace("{{APP_VERSION}}", safe(appVersion))
            .replace("{{SPRING_PROFILE}}", safe(springProfile))
            .replace("{{EXTERNAL_SERVICE}}", safe(externalServiceName))
            .replace("{{S3_BUCKET}}", safe(s3ProfileBucket))
            .replace("{{S3_REGION}}", safe(s3Region))
            .replace("{{SERVER_TIME}}", safe(now));
    }

    @GetMapping("/health")
    public String health() {
        return "ok " + Instant.now().toString();
    }

    private static String safe(String input) {
        if (input == null) {
            return "";
        }
        return input
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;");
    }
}
