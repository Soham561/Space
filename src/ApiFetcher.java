import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import org.json.JSONObject;

public class ApiFetcher {
    private static final String NASA_API_URL = "https://api.nasa.gov/planetary/apod?api_key=";

    public static String fetchSpaceFact(Properties config) {
        try {
            String apiKey = config.getProperty("nasa.api.key");
            URL url = new URL(NASA_API_URL + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            String title = json.getString("title");
            String explanation = json.getString("explanation");
            String date = json.getString("date");
            String imageUrl = json.getString("url");

            // Build beautiful HTML content

            StringBuilder html = new StringBuilder();
            html.append("<div style=\""
                    + "max-width: 700px;"
                    + "margin: 0 auto;"
                    + "font-family: 'Segoe UI', Roboto, Oxygen, Ubuntu, sans-serif;"
                    + "background: linear-gradient(135deg, #1a1a2e, #16213e);"
                    + "color: #ffffff;"
                    + "border-radius: 16px;"
                    + "overflow: hidden;"
                    + "box-shadow: 0 12px 40px rgba(0, 0, 0, 0.6);"
                    + "position: relative;"
                    + "border: 1px solid rgba(255, 255, 255, 0.1);"
                    + "\">");

            // Header with nebula effect
            html.append("<div style=\""
                    + "padding: 35px;"
                    + "text-align: center;"
                    + "background: rgba(10, 15, 40, 0.7);"
                    + "border-bottom: 1px solid rgba(255, 255, 255, 0.1);"
                    + "position: relative;"
                    + "\">");
            html.append("<h1 style=\""
                    + "margin: 0;"
                    + "font-size: 38px;"
                    + "font-weight: 800;"
                    + "background: linear-gradient(to right, #ff6b6b, #ffa502);"
                    + "-webkit-background-clip: text;"
                    + "background-clip: text;"
                    + "color: transparent;"
                    + "text-shadow: 0 0 20px rgba(255, 107, 107, 0.4);"
                    + "letter-spacing: 0.5px;"
                    + "padding-bottom: 15px;"
                    + "\">Cosmic Discovery</h1>");
            html.append("<div style=\""
                    + "height: 5px;"
                    + "width: 150px;"
                    + "background: linear-gradient(to right, #ff6b6b, #ffa502);"
                    + "margin: 0 auto;"
                    + "border-radius: 3px;"
                    + "box-shadow: 0 0 15px rgba(255, 165, 2, 0.4);"
                    + "\"></div>");
            html.append("</div>");

            // Content container
            html.append("<div style=\"padding: 30px;\">");

            // Title and date
            html.append("<div style=\"margin-bottom: 25px;\">");
            html.append("<h2 style=\""
                    + "margin: 0 0 10px 0;"
                    + "font-size: 32px;"
                    + "color: #ffd166;"
                    + "font-weight: 700;"
                    + "text-shadow: 0 0 10px rgba(255, 209, 102, 0.2);"
                    + "\">" + title + "</h2>");
            html.append("<div style=\""
                    + "display: flex;"
                    + "align-items: center;"
                    + "gap: 10px;"
                    + "margin-bottom: 15px;"
                    + "\">");
            html.append("<span style=\""
                    + "background: linear-gradient(45deg, #ff6b6b, #ffa502);"
                    + "color: #000;"
                    + "padding: 6px 14px;"
                    + "border-radius: 20px;"
                    + "font-weight: 600;"
                    + "font-size: 14px;"
                    + "box-shadow: 0 0 10px rgba(255, 165, 2, 0.3);"
                    + "\">" + date + "</span>");
            html.append("<span style=\""
                    + "background: rgba(255, 255, 255, 0.1);"
                    + "color: #ffd166;"
                    + "padding: 6px 14px;"
                    + "border-radius: 20px;"
                    + "font-weight: 600;"
                    + "font-size: 14px;"
                    + "\"> NASA Official</span>");
            html.append("</div>");
            html.append("</div>");

            // Image with floating effect
            html.append("<div style=\""
                    + "position: relative;"
                    + "border-radius: 16px;"
                    + "overflow: hidden;"
                    + "box-shadow: 0 0 40px rgba(255, 165, 2, 0.3);"
                    + "margin-bottom: 35px;"
                    + "border: 2px solid rgba(255, 255, 255, 0.15);"
                    + "animation: float 8s ease-in-out infinite;"
                    + "\">");
            html.append("<img src='" + imageUrl + "' alt='NASA Astronomy Picture of the Day' "
                    + "style='"
                    + "display: block;"
                    + "width: 100%;"
                    + "transition: transform 0.5s ease;"
                    + "'/>");
            html.append("<div style=\""
                    + "position: absolute;"
                    + "top: 0;"
                    + "left: 0;"
                    + "right: 0;"
                    + "bottom: 0;"
                    + "background: radial-gradient(circle at center, rgba(255, 107, 107, 0.1) 0%, transparent 70%);"
                    + "pointer-events: none;"
                    + "\"></div>");
            html.append("</div>");

            // Explanation
            html.append("<div style=\""
                    + "position: relative;"
                    + "z-index: 2;"
                    + "\">");
            html.append("<h3 style=\""
                    + "color: #ffd166;"
                    + "font-size: 22px;"
                    + "margin-top: 0;"
                    + "margin-bottom: 15px;"
                    + "padding-left: 10px;"
                    + "border-left: 3px solid #ffa502;"
                    + "\">Explanation</h3>");
            html.append("<p style=\""
                    + "line-height: 1.7;"
                    + "font-size: 18px;"
                    + "color: #e0e0ff;"
                    + "background: rgba(0, 0, 0, 0.3);"
                    + "padding: 25px;"
                    + "border-radius: 12px;"
                    + "backdrop-filter: blur(4px);"
                    + "border: 1px solid rgba(255, 255, 255, 0.1);"
                    + "box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);"
                    + "\">" + explanation + "</p>");
            html.append("</div>");

            // Footer
            html.append("<div style=\""
                    + "margin-top: 40px;"
                    + "text-align: center;"
                    + "padding: 25px 0 15px;"
                    + "border-top: 1px solid rgba(255, 255, 255, 0.1);"
                    + "\">");
            html.append("<p style=\""
                    + "margin: 0;"
                    + "font-size: 16px;"
                    + "color: #ffa502;"
                    + "display: flex;"
                    + "align-items: center;"
                    + "justify-content: center;"
                    + "gap: 10px;"
                    + "font-weight: 600;"
                    + "\">"
                    + "Powered by NASA APOD API</p>");
            html.append("<p style=\""
                    + "margin: 10px 0 0;"
                    + "font-size: 14px;"
                    + "color: #8fbcbb;"
                    + "\">Cosmic Insights • Exploring the Universe</p>");
            html.append("</div>");

            html.append("</div>"); // Close content container

            // Decorative cosmic elements
            html.append("<div style=\""
                    + "position: absolute;"
                    + "top: 20%;"
                    + "right: 5%;"
                    + "width: 100px;"
                    + "height: 100px;"
                    + "border-radius: 50%;"
                    + "background: radial-gradient(circle, rgba(255, 107, 107, 0.3) 0%, transparent 70%);"
                    + "filter: blur(10px);"
                    + "z-index: 1;"
                    + "\"></div>");
            html.append("<div style=\""
                    + "position: absolute;"
                    + "bottom: 10%;"
                    + "left: 5%;"
                    + "width: 80px;"
                    + "height: 80px;"
                    + "border-radius: 50%;"
                    + "background: radial-gradient(circle, rgba(255, 209, 102, 0.3) 0%, transparent 70%);"
                    + "filter: blur(8px);"
                    + "z-index: 1;"
                    + "\"></div>");

            // CSS animations
            html.append("<style>"
                    + "@keyframes float {"
                    + "  0% { transform: translateY(0px); }"
                    + "  50% { transform: translateY(-15px); }"
                    + "  100% { transform: translateY(0px); }"
                    + "}"
                    + "img:hover {"
                    + "  transform: scale(1.03);"
                    + "}"
                    + "</style>");

            html.append("</div>"); // Close main container

            return html.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "<div style='"
                    + "padding: 40px;"
                    + "font-family: sans-serif;"
                    + "background: linear-gradient(135deg, #1a1a2e, #16213e);"
                    + "color: #ff6b6b;"
                    + "border-radius: 16px;"
                    + "text-align: center;"
                    + "box-shadow: 0 10px 30px rgba(0, 0, 0, 0.4);"
                    + "'>"
                    + "<h2 style='color:#ffd166; margin-top:0;'>Cosmic Connection Failed</h2>"
                    + "<p>Failed to retrieve space data. Please check your API key and network connection.</p>"
                    + "<p>Error details: " + e.getMessage() + "</p>"
                    + "</div>";
        }
    }
}