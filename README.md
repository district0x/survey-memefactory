# survey-memefactory

[survey.memefactory.io](https://survey.memefactory.io/)

## Development
Start server: 
```bash
ganache-cli -p 8549
lein repl
(start-server!)
node dev-server/memefactory.js
```

Start UI:
```bash
lein repl
(start-ui!)
# go to http://localhost:4235/
```

Compile styles: 
```bash
lein garden auto
```
