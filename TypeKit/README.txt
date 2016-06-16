CONTENTS OF THE FOLDER

Typekit: Eclipse Java Project Folder for TypeKit Fetch


Typekit1: Eclipse Java Project Folder for TypeKit Family, TypeKit Family Variation, TypeKit Post


java-son.jar: The code makes use of an external library java-json.jar to parse the JSON response. It is already included in the typekit.jar file and  Typekit Folder. 


typekitfamily.jar file is used to make requests and fetch the information about a font family. 




typekitpost.jar file is used to post a new kit to an account using the public TypeKit API.

More info: https://typekit.com/docs/api/v1/:format/kits

SYNTAX : java -jar typekit.jar <token> 




2. TypeKit Family 

More info: https://typekit.com/docs/api/v1/:format/families/:family

SYNTAX : java -jar typekitfamily.jar <family_id>

{"family": {
    "foundry": {
        "name": "Google Android",
        "slug": "google-android"
    },
    "variations": [
        {
            "fvd": "n4",
            "link": "/api/v1/json/families/pcpv/n4",
            "name": "Droid Serif Regular",
            "id": "pcpv:n4"
        },
        {
            "fvd": "i4",
            "link": "/api/v1/json/families/pcpv/i4",
            "name": "Droid Serif Italic",
            "id": "pcpv:i4"
        },
        {
            "fvd": "n7",
            "link": "/api/v1/json/families/pcpv/n7",
            "name": "Droid Serif Bold",
            "id": "pcpv:n7"
        },
        {
            "fvd": "i7",
            "link": "/api/v1/json/families/pcpv/i7",
            "name": "Droid Serif Bold Italic",
            "id": "pcpv:i7"
        }
    ],
    "name": "Droid Serif",
    "libraries": [
        {
            "link": "/api/v1/json/libraries/trial",
            "name": "Trial Library",
            "id": "trial"
        },
        {
            "link": "/api/v1/json/libraries/personal",
            "name": "Personal Library",
            "id": "personal"
        },
        {
            "link": "/api/v1/json/libraries/full",
            "name": "Full Library",
            "id": "full"
        },
        {
            "link": "/api/v1/json/libraries/enterprise",
            "name": "Enterprise Library",
            "id": "enterprise"
        }
    ],
    "browse_info": {
        "recommended_for": ["paragraphs"],
        "capitals": ["uppercase-lowercase"],
        "contrast": ["regular"],
        "width": ["regular"],
        "weight": ["regular"],
        "language": [
            "ca",
            "cs",
            "de",
            "el",
            "en",
            "es",
            "fr",
            "hu",
            "it",
            "mt",
            "nl",
            "pl",
            "pt",
            "ru",
            "sl",
            "sv",
            "tr",
            "vi"
        ],
        "x_height": ["high"],
        "classification": ["serif"],
        "number_style": ["uppercase"]
    },
    "description": "Droid Serif is a contemporary serif typeface family designed for comfortable reading on screen. Droid Serif is slightly condensed to maximize the amount of text displayed on small screens. Vertical stress, sturdy serifs and open forms contribute to the readability.",
    "id": "pcpv",
    "web_link": "http://typekit.com/fonts/droid-serif",
    "css_stack": "serif",
    "slug": "droid-serif"
}}




3.TypeKit Family Variation 

A command line interface in Java to fetch information about a font family and its variation using the public Typekit API.

More Info: https://typekit.com/docs/api/v1/:format/families/:family/:variation

Example:

SYNTAX : java -jar typekitvariation.jar <family_name> <variation_id>

java -jar typekitvariation.jar droid-sans n4 
{"variation": {
    "font_weight": "400",
    "foundry": {
        "name": "Google Android",
        "slug": "google-android"
    },
    "name": "Droid Sans Regular",
    "libraries": [
        {
            "link": "/api/v1/json/libraries/trial",
            "name": "Trial Library",
            "id": "trial"
        },
        {
            "link": "/api/v1/json/libraries/personal",
            "name": "Personal Library",
            "id": "personal"
        },
        {
            "link": "/api/v1/json/libraries/full",
            "name": "Full Library",
            "id": "full"
        },
        {
            "link": "/api/v1/json/libraries/enterprise",
            "name": "Enterprise Library",
            "id": "enterprise"
        }
    ],
    "font_variant": "normal",
    "postscript_name": "DroidSans",
    "id": "gkmg:n4",
    "font_style": "normal",
    "family": {
        "link": "/api/v1/json/families/gkmg",
        "name": "Droid Sans",
        "id": "gkmg"
    }
}}




4. TypeKit Post 

A command line interface in Java to post a new kit to an account using the public TypeKit API.

More info: https://typekit.com/docs/api/v1/:format/kits

NOTE: For Trial TypeKit post will work only if test-account has 0 (zero) kits. 

Example:

SYNTAX : java -jar typekitpost.jar <token> <kit_name> <kit_domain_name>

java -jar typekitpost.jar 1f28efeb54e80f6340f2c79b41cdb30ddb4cdd47 newkit adobe.com 
New Kit has been added!
{"kit": {
    "analytics": false,
    "optimize_performance": false,
    "name": "newkit",
    "domains": ["adobe.com"],
    "id": "qpt2ryw",
    "families": []
}}

VERIFY

curl https://typekit.com/api/v1/json/kits?token=1f28efeb54e80f6340f2c79b41cdb30ddb4cdd47
{"kits":[{"id":"qpt2ryw","link":"/api/v1/json/kits/qpt2ryw"}]}
