module.exports = {
    html: {
      playground: {
        enabled: true,
        hidden: false,
        env: "development",
        environments: {
          development: {
            url: "http://localhost:5000/",
          },
          staging: {
            url: "http://51.250.24.236/",
          },
        }
      }
    }
  };