package edu.cnm.deepdive.pipebendcalcv5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import edu.cnm.deepdive.pipebendcalcv5.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

  private WebView webHtmlCss;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,
        getSupportFragmentManager());
    ViewPager viewPager = findViewById(R.id.view_pager);
    viewPager.setAdapter(sectionsPagerAdapter);
    TabLayout tabs = findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);

    webHtmlCss = (WebView) findViewById(R.id.pipe_bend_calc);

    WebSettings ws = webHtmlCss.getSettings();
    ws.setJavaScriptEnabled(true);

    webHtmlCss.loadUrl("file:///android_asset/index.html");
    webHtmlCss.getSettings().setJavaScriptEnabled(true);
    webHtmlCss.setWebChromeClient(new WebChromeClient() {

      @Override
      public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        AlertDialog dialog = new AlertDialog.Builder(view.getContext()).
            setTitle("").
            setMessage(message).
            setPositiveButton("OK", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                //do nothing
              }
            }).create();
        dialog.show();
        result.confirm();
        return true;
      }
    });
  }
}