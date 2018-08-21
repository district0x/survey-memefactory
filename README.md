# survey-memefactory

[survey.memefactory.io](https://survey.memefactory.io/)

## Development
Start server
```bash
(start-server!)
node dev-server/memefactory.js
```
Note, server assumes mainnet node running on localhost port 8545

Start UI:
```bash
lein repl
(start-ui!)
# go to http://localhost:4235/
```
Note, you don't need to start server in order to develop purely UI. It fetches data from production server.

Compile styles: 
```bash
lein garden auto
```
