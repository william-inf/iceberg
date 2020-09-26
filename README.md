# Iceberg

Iceberg is a simple application that can probe REST endpoints and present selected data on a web page.

*Note: Under active development and not functional yet.*

## Installation

Create a config file at ```/opt/iceberg/iceberg_config.conf```

Follow the below format and include the addresses you would like to probe. 

*Note: In future this will be generated and edited via web portal.*

```bash
{
	"urls": [{
		"name": "<website name>",
		"addr": "http://example.xyz",
		"path": "/status",
		"type": "Unauthenticated",
		"response": {
			"type": "JSON",
			"values": [{
				"key": "version",
				"label": "Version"
			}, ...]
		}
	} ... ],
    "settings" : {
        <app settings>
    },
    "groups" : [{
        ...
    }]
}
```

## Todo

- Add supporting URLs to groups
- Build config from web portal

## Build

To assemble to JAR file

```bash
./gradlew assembleServerAndClient
```

## Usage

Navigate to http://localhost:8080 (or configured address)


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)