package placeme.octopusites.com.placeme;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class LicenseActivity extends AppCompatActivity {


    TextView mainheading,subheading,license1,content1,license2,content2,license3,content3,license4,content4,license5,content5,license6,content6,license7,content7,license8,content8,license9,content9,license10,content10;
    TextView license11, content11, license12, content12, license13, content13, license14, content14, license15, content15, license16, content16, license17, content17, license18, content18, license19, content19, license20, content20, license21, content21, license22, content22, license23, content23, license24, content24;
    String mainheadstr = "Libraries We Use";
    String subheadingstr = "The following sets forth attribution notices for third party software that may be contained in portion of the place me product. we thank the open source community for all of their contribution.";

    String licencestr1 = "support-v4";
    String contentstr1 = " Copyright (C) 2014 The Android Open Source Project\n" +
            "    Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "    you may not use this file except in compliance with the License.\n" +
            "    You may obtain a copy of the License at\n" +
            "    http://www.apache.org/licenses/LICENSE-2.0\n" +
            "    Unless required by applicable law or agreed to in writing, software\n" +
            "    distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "    See the License for the specific language governing permissions and\n" +
            "    limitations under the License.";

    String licencestr2 = "dexmaker";
    String contentstr2 = "Copyright (C) 2011 The Android Open Source Project\n" +
            " \n" +
            "  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "  you may not use this file except in compliance with the License.\n" +
            "  You may obtain a copy of the License at\n" +
            " \n" +
            "       http://www.apache.org/licenses/LICENSE-2.0\n" +
            " \n" +
            "  Unless required by applicable law or agreed to in writing, software\n" +
            "  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "  See the License for the specific language governing permissions and\n" +
            "  limitations under the License.";

    String licencestr3 = "appcompat";
    String contentstr3 = "Copyright (C) 2012 The Android Open Source Project\n" +
            "     Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "     you may not use this file except in compliance with the License.\n" +
            "     You may obtain a copy of the License at\n" +
            "          http://www.apache.org/licenses/LICENSE-2.0\n" +
            "     Unless required by applicable law or agreed to in writing, software\n" +
            "     distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "     See the License for the specific language governing permissions and\n" +
            "     limitations under the License.\t";

    String licencestr4 = "constraint-layout";
    String contentstr4 =  "Copyright 2017 The Android Open Source Project, Inc.\n" +
            "\n" +
            "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at\n" +
            "\n" +
            "http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.\n" +
            "© 2017 GitHub, Inc.";

    String licencestr5 = "circleimageview";
    String contentstr5 = "Copyright 2014 - 2017 Henning Dodenhof\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "you may not use this file except in compliance with the License.\n" +
            "You may obtain a copy of the License at\n" +
            "\n" +
            "    http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "Unless required by applicable law or agreed to in writing, software\n" +
            "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "See the License for the specific language governing permissions and\n" +
            "limitations under the License.";


    String licencestr6 = "design";
    String contentstr6 = " Copyright (C) 2015 The Android Open Source Project\n" +
            "     Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "     you may not use this file except in compliance with the License.\n" +
            "     You may obtain a copy of the License at\n" +
            "          http://www.apache.org/licenses/LICENSE-2.0\n" +
            "     Unless required by applicable law or agreed to in writing, software\n" +
            "     distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "     See the License for the specific language governing permissions and\n" +
            "     limitations under the License.";

    String licencestr7 = "recyclerview";
    String contentstr7 = "Copyright (C) 2015 The Android Open Source Project\n" +
            "\n" +
            " Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            " you may not use this file except in compliance with the License.\n" +
            " You may obtain a copy of the License at\n" +
            "\n" +
            "      http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            " Unless required by applicable law or agreed to in writing, software\n" +
            " distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            " WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            " See the License for the specific language governing permissions and\n" +
            " limitations under the License.";

    String licencestr8 = "wheelview";
    String contentstr8 =  "Copyright (C) 2016 venshine.cn@gmail.com\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "you may not use this file except in compliance with the License.\n" +
            "You may obtain a copy of the License at\n" +
            "\n" +
            "http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "Unless required by applicable law or agreed to in writing, software\n" +
            "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "See the License for the specific language governing permissions and\n" +
            "limitations under the License.";

    String licencestr9 = "materialsearchview";
    String contentstr9 = "Copyright 2015 Miguel Catalan Bañuls\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "you may not use this file except in compliance with the License.\n" +
            "You may obtain a copy of the License at\n" +
            "\n" +
            "\thttp://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "Unless required by applicable law or agreed to in writing, software\n" +
            "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "See the License for the specific language governing permissions and\n" +
            "limitations under the License.";

    String licencestr10 = "multipicker";
    String contentstr10 =   "Copyright 2016 Kumar Bibek\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "you may not use this file except in compliance with the License.\n" +
            "You may obtain a copy of the License at\n" +
            "\n" +
            "http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "Unless required by applicable law or agreed to in writing, software\n" +
            "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "See the License for the specific language governing permissions and\n" +
            "limitations under the License.";

    String licencestr11 = "mockito-core";
    String contentstr11 =   "The MIT License\n" +
            "\n" +
            "Copyright (c) 2007 Mockito contributors\n" +
            "\n" +
            "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
            "of this software and associated documentation files (the \"Software\"), to deal\n" +
            "in the Software without restriction, including without limitation the rights\n" +
            "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
            "copies of the Software, and to permit persons to whom the Software is\n" +
            "furnished to do so, subject to the following conditions:\n" +
            "\n" +
            "The above copyright notice and this permission notice shall be included in\n" +
            "all copies or substantial portions of the Software.\n" +
            "\n" +
            "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
            "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
            "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
            "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
            "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
            "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n" +
            "THE SOFTWARE.\n";

    String licencestr12 = "glide";
    String contentstr12 = "Copyright 2014 Google, Inc. All rights reserved.\n" +
            "\n" +
            "Redistribution and use in source and binary forms, with or without modification, are\n" +
            "permitted provided that the following conditions are met:\n" +
            "\n" +
            "   1. Redistributions of source code must retain the above copyright notice, this list of\n" +
            "         conditions and the following disclaimer.\n" +
            "\n" +
            "   2. Redistributions in binary form must reproduce the above copyright notice, this list\n" +
            "         of conditions and the following disclaimer in the documentation and/or other materials\n" +
            "         provided with the distribution.\n" +
            "\n" +
            "THIS SOFTWARE IS PROVIDED BY GOOGLE, INC. ``AS IS'' AND ANY EXPRESS OR IMPLIED\n" +
            "WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND\n" +
            "FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL GOOGLE, INC. OR\n" +
            "CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR\n" +
            "CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR\n" +
            "SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON\n" +
            "ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING\n" +
            "NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF\n" +
            "ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n" +
            "\n" +
            "The views and conclusions contained in the software and documentation are those of the\n" +
            "authors and should not be interpreted as representing official policies, either expressed\n" +
            "or implied, of Google, Inc.\n";

    String licencestr13 = "firebase";
    String contentstr13 = "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:\n" +
            "\n" +
            "The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.\n" +
            "\n" +
            "THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.\n" +
            "\t";

    String licencestr14 = "joda-time";
    String contentstr14 = "  Copyright [yyyy] [name of copyright owner]\n" +
            "\n" +
            "   Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "   you may not use this file except in compliance with the License.\n" +
            "   You may obtain a copy of the License at\n" +
            "\n" +
            "       http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "   Unless required by applicable law or agreed to in writing, software\n" +
            "   distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "   See the License for the specific language governing permissions and\n" +
            "   limitations under the License.";

    String licencestr15 = "cardview";
    String contentstr15 = "Copyright 2017 The Android Open Source Project, Inc.\n" +
            "\n" +
            "Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at\n" +
            "\n" +
            "http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.\n" +
            "\t";

    String licencestr16 = "annotations";
    String contentstr16 = "\n" +
            "\tCopyright (C) 2014 The Android Open Source Project\n" +
            " \n" +
            "  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "  you may not use this file except in compliance with the License.\n" +
            "  You may obtain a copy of the License at\n" +
            " \n" +
            "       http://www.apache.org/licenses/LICENSE-2.0\n" +
            " \n" +
            "  Unless required by applicable law or agreed to in writing, software\n" +
            "  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "  See the License for the specific language governing permissions and\n" +
            "  limitations under the License.";

    String licencestr17 = "squareup:fest";
    String contentstr17 = "Copyright 2013 Square, Inc.\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "you may not use this file except in compliance with the License.\n" +
            "You may obtain a copy of the License at\n" +
            "\n" +
            "   http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "Unless required by applicable law or agreed to in writing, software\n" +
            "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "See the License for the specific language governing permissions and\n" +
            "limitations under the License.";

    String licencestr18 = "dexmaker-mockito";
    String contentstr18 = " Copyright (C) 2012 The Android Open Source Project\n" +
            " \n" +
            "  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "  you may not use this file except in compliance with the License.\n" +
            "  You may obtain a copy of the License at\n" +
            " \n" +
            "       http://www.apache.org/licenses/LICENSE-2.0\n" +
            " \n" +
            "  Unless required by applicable law or agreed to in writing, software\n" +
            "  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "  See the License for the specific language governing permissions and\n" +
            "  limitations under the License.";

    String licencestr19 = "TagsEditText";
    String contentstr19 = "Copyright {yyyy} {name of copyright owner}\n" +
            "\n" +
            "   Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "   you may not use this file except in compliance with the License.\n" +
            "   You may obtain a copy of the License at\n" +
            "\n" +
            "   http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "   Unless required by applicable law or agreed to in writing, software\n" +
            "   distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "   See the License for the specific language governing permissions and\n" +
            "   limitations under the License.";

    String licencestr20 = "crash";
    String contentstr20 =  "  Copyright [yyyy] [name of copyright owner]\n" +
            "\n" +
            "   Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "   you may not use this file except in compliance with the License.\n" +
            "   You may obtain a copy of the License at\n" +
            "\n" +
            "       http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "   Unless required by applicable law or agreed to in writing, software\n" +
            "   distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either  express or implied.\n" +
            "   See the License for the specific language governing permissions  and\n" +
            "   limitations under the License.\n";

    String licencestr21 = "multidex";
    String contentstr21 = "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "   you may not use this file except in compliance with the License.\n" +
            "   You may obtain a copy of the License at\n" +
            "\n" +
            "       http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "   Unless required by applicable law or agreed to in writing, software\n" +
            "   distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "   See the License for the specific language governing permissions and\n" +
            "   limitations under the License.\n";

    String licencestr22 = "Ripple Effect";
    String contentstr22 = "The MIT License (MIT)\n" +
            "\n" +
            "Copyright (c) 2014 Robin Chutaux\n" +
            "\n" +
            "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
            "of this software and associated documentation files (the \"Software\"), to deal\n" +
            "in the Software without restriction, including without limitation the rights\n" +
            "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
            "copies of the Software, and to permit persons to whom the Software is\n" +
            "furnished to do so, subject to the following conditions:";


    String licencestr23 = "advancedluban";
    String contentstr23 = "Copyright 2016 shaohui10086\n" +
            "\n" +
            "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            "you may not use this file except in compliance with the License.\n" +
            "You may obtain a copy of the License at\n" +
            "\n" +
            "   http://www.apache.org/licenses/LICENSE-2.0\n" +
            "\n" +
            "Unless required by applicable law or agreed to in writing, software\n" +
            "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "See the License for the specific language governing permissions and\n" +
            "limitations under the License.\n";

    String licencestr24 = "Nunito & Righteous";
    String contentstr24 = "Copyright (c),Nicolas Spalinger & Victor Gaultney, 2007-02-26\n" +
            "THE FONT SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND,\n" +
            "EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO ANY WARRANTIES OF\n" +
            "MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT\n" +
            "OF COPYRIGHT, PATENT, TRADEMARK, OR OTHER RIGHT. IN NO EVENT SHALL THE\n" +
            "COPYRIGHT HOLDER BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,\n" +
            "INCLUDING ANY GENERAL, SPECIAL, INDIRECT, INCIDENTAL, OR CONSEQUENTIAL\n" +
            "DAMAGES, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING\n" +
            "FROM, OUT OF THE USE OR INABILITY TO USE THE FONT SOFTWARE OR FROM\n" +
            "OTHER DEALINGS IN THE FONT SOFTWARE.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Open Source Licenses");
        ab.setDisplayHomeAsUpEnabled(true);

        mainheading = (TextView) findViewById(R.id.mainheading);
        subheading = (TextView)findViewById(R.id.subheading);

        license1 = (TextView)findViewById(R.id.license1);
        content1 = (TextView)findViewById(R.id.content1);

        license2 = (TextView)findViewById(R.id.license2);
        content2 = (TextView)findViewById(R.id.content2);

        license3 = (TextView)findViewById(R.id.license3);
        content3 = (TextView)findViewById(R.id.content3);

        license4 = (TextView)findViewById(R.id.license4);
        content4 = (TextView)findViewById(R.id.content4);

        license5 = (TextView)findViewById(R.id.license5);
        content5 = (TextView)findViewById(R.id.content5);

        license6 = (TextView)findViewById(R.id.license6);
        content6 = (TextView)findViewById(R.id.content6);

        license7 = (TextView)findViewById(R.id.license7);
        content7 = (TextView)findViewById(R.id.content7);

        license8 = (TextView)findViewById(R.id.license8);
        content8 = (TextView)findViewById(R.id.content8);

        license9 = (TextView)findViewById(R.id.license9);
        content9 = (TextView)findViewById(R.id.content9);

        license10 = (TextView)findViewById(R.id.license10);
        content10 = (TextView)findViewById(R.id.content10);

        license11 = (TextView)findViewById(R.id.license11);
        content11 = (TextView)findViewById(R.id.content11);

        license12 = (TextView)findViewById(R.id.license12);
        content12 = (TextView)findViewById(R.id.content12);

        license13 = (TextView)findViewById(R.id.license13);
        content13 = (TextView)findViewById(R.id.content13);

        license14 = (TextView)findViewById(R.id.license14);
        content14 = (TextView)findViewById(R.id.content14);

        license15 = (TextView)findViewById(R.id.license15);
        content15 = (TextView)findViewById(R.id.content15);

        license16 = (TextView)findViewById(R.id.license16);
        content16 = (TextView)findViewById(R.id.content16);

        license17 = (TextView)findViewById(R.id.license17);
        content17 = (TextView)findViewById(R.id.content17);

        license18 = (TextView)findViewById(R.id.license18);
        content18 = (TextView)findViewById(R.id.content18);

        license19 = (TextView)findViewById(R.id.license19);
        content19 = (TextView)findViewById(R.id.content19);

        license20 = (TextView)findViewById(R.id.license20);
        content20 = (TextView)findViewById(R.id.content20);

        license21 = (TextView) findViewById(R.id.license21);
        content21 = (TextView) findViewById(R.id.content21);

        license22 = (TextView) findViewById(R.id.license22);
        content22 = (TextView) findViewById(R.id.content22);

        license23 = (TextView) findViewById(R.id.license23);
        content23 = (TextView) findViewById(R.id.content23);

        license24 = (TextView) findViewById(R.id.license24);
        content24 = (TextView) findViewById(R.id.content24);


        Typeface custom_font6 = Z.getLight(this);
        Typeface custom_font7 = Z.getBold(this);

        mainheading.setTypeface(Z.getBold(this));
        subheading.setTypeface(custom_font6);

        license1.setTypeface(custom_font7);
        content1.setTypeface(custom_font6);

        license2.setTypeface(custom_font7);
        content2.setTypeface(custom_font6);

        license3.setTypeface(custom_font7);
        content3.setTypeface(custom_font6);

        license4.setTypeface(custom_font7);
        content4.setTypeface(custom_font6);

        license5.setTypeface(custom_font7);
        content5.setTypeface(custom_font6);

        license6.setTypeface(custom_font7);
        content6.setTypeface(custom_font6);

        license7.setTypeface(custom_font7);
        content7.setTypeface(custom_font6);

        license8.setTypeface(custom_font7);
        content8.setTypeface(custom_font6);

        license9.setTypeface(custom_font7);
        content9.setTypeface(custom_font6);

        license10.setTypeface(custom_font7);
        content10.setTypeface(custom_font6);

        license11.setTypeface(custom_font7);
        content11.setTypeface(custom_font6);

        license12.setTypeface(custom_font7);
        content12.setTypeface(custom_font6);

        license13.setTypeface(custom_font7);
        content13.setTypeface(custom_font6);

        license14.setTypeface(custom_font7);
        content14.setTypeface(custom_font6);

        license15.setTypeface(custom_font7);
        content15.setTypeface(custom_font6);

        license16.setTypeface(custom_font7);
        content16.setTypeface(custom_font6);

        license17.setTypeface(custom_font7);
        content17.setTypeface(custom_font6);

        license18.setTypeface(custom_font7);
        content18.setTypeface(custom_font6);

        license19.setTypeface(custom_font7);
        content19.setTypeface(custom_font6);

        license20.setTypeface(custom_font7);
        content20.setTypeface(custom_font6);

        license21.setTypeface(custom_font7);
        content21.setTypeface(custom_font6);

        license22.setTypeface(custom_font7);
        content22.setTypeface(custom_font6);

        license23.setTypeface(custom_font7);
        content23.setTypeface(custom_font6);

        license24.setTypeface(custom_font7);
        content24.setTypeface(custom_font6);


        mainheading.setText(mainheadstr);
        subheading.setText(subheadingstr);

        license1.setText(licencestr1);
        content1.setText(contentstr1);

        license2.setText(licencestr2);
        content2.setText(contentstr2);

        license3.setText(licencestr3);
        content3.setText(contentstr3);

        license4.setText(licencestr4);
        content4.setText(contentstr4);

        license5.setText(licencestr5);
        content5.setText(contentstr5);

        license6.setText(licencestr6);
        content6.setText(contentstr6);

        license7.setText(licencestr7);
        content7.setText(contentstr7);

        license8.setText(licencestr8);
        content8.setText(contentstr8);

        license9.setText(licencestr9);
        content9.setText(contentstr9);

        license10.setText(licencestr10);
        content10.setText(contentstr10);

        license11.setText(licencestr11);
        content11.setText(contentstr11);

        license12.setText(licencestr12);
        content12.setText(contentstr12);

        license13.setText(licencestr13);
        content13.setText(contentstr13);

        license14.setText(licencestr14);
        content14.setText(contentstr14);

        license15.setText(licencestr15);
        content15.setText(contentstr15);

        license16.setText(licencestr16);
        content16.setText(contentstr16);

        license17.setText(licencestr17);
        content17.setText(contentstr17);

        license18.setText(licencestr18);
        content18.setText(contentstr18);

        license19.setText(licencestr19);
        content19.setText(contentstr19);

        license20.setText(licencestr20);
        content20.setText(contentstr20);

        license21.setText(licencestr21);
        content21.setText(contentstr21);

        license22.setText(licencestr22);
        content22.setText(contentstr22);

        license23.setText(licencestr23);
        content23.setText(contentstr23);

        license24.setText(licencestr24);
        content24.setText(contentstr24);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

}
