package com.example.sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private static final String URL = "http://192.168.1.24/member.php?";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((TextView)findViewById(R.id.textView2)).setText("");
		// 検索ボタン
		((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((TextView)findViewById(R.id.textView2)).setText("検索中...");
				search(((TextView)findViewById(R.id.editText1)).getText().toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 検索。
	 * 
	 * @return
	 */
	private void search(String keyword) {

		final MainActivity activity = this;

		/**
		 * 非同期処理クラス。
		 */
		AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {

			/**
			 * バックグラウンドスレッドで実行。
			 */
			@Override
			protected String doInBackground(String... params) {
				try {
					HttpGet http = new HttpGet(URL + "keyword=" + params[0]);
					DefaultHttpClient client = new DefaultHttpClient();
					HttpResponse response = client.execute(http);
					int status = response.getStatusLine().getStatusCode();
					if (HttpStatus.SC_OK != status) {
						throw new Exception("!ok");
					}
					return EntityUtils.toString(response.getEntity(), "UTF-8");
				} catch (Exception e) {
					return e.toString();
				}
			}

			/**
			 * doInBackground() 実行後に UI スレッドを実行。
			 */
			@Override
			protected void onPostExecute(String result) {
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						activity, android.R.layout.simple_list_item_1);
				int count = 0;
				Pattern p = Pattern.compile(
						"(?is)<tr>.*?<td>(.*?)</td>.*?<td><a .*?>(.*?)</a></td>.*?<td>(.*?)</td>.*?</tr>");
				Matcher m = p.matcher(result);
				while (m.find()) {
					++ count;
					adapter.add(m.group(1) + " " + m.group(2) + " " + m.group(3));
				}
				((TextView)findViewById(R.id.textView2)).setText(count + " 件見つかりました");
				((ListView)findViewById(R.id.listView1)).setAdapter(adapter);
			}
		};

		// 非同期処理を実行
		task.execute(keyword);
	}
}
