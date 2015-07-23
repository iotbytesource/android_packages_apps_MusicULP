/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.music;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetUlpBypass extends Activity implements RadioGroup.OnCheckedChangeListener
{
    private Button mSaveButton;
    private static boolean mTempUlpBypass = false;
    private static boolean mUlpBypass = false;
    RadioGroup mGroup;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ulp_bypass);
        mGroup = (RadioGroup)findViewById(R.id.ulp_bypass);
        mGroup.setOnCheckedChangeListener(this);

        if (mUlpBypass)
            mGroup.check(R.id.radio_on);
        else
            mGroup.check(R.id.radio_off);

        mSaveButton = (Button) findViewById(R.id.button_set);
        mSaveButton.setOnClickListener(mOpenClicked);
    }

    public void onCheckedChanged(RadioGroup group, int checkedid)
    {
        if (group == mGroup) {
            if (checkedid == R.id.radio_on)
                mTempUlpBypass = true;
            else if (checkedid == R.id.radio_off)
                mTempUlpBypass = false;
        }
    }

    private View.OnClickListener mOpenClicked = new View.OnClickListener() {
        public void onClick(View v)
        {
            mUlpBypass = mTempUlpBypass;
            Intent intent = new Intent();
            intent.putExtra("com.android.music.ulp_bypass", mUlpBypass);
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
