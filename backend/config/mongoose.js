const mongoose =  require('mongoose');

const connectDB = async () => {
  // get notifications about events
  mongoose.connection.on('connected', () =>
    console.log('Mongoose connection is open.')
  )

  mongoose.connection.on('error', err =>
    console.error(`Mongoose connection error has occurred: ${err}`)
  )

  mongoose.connection.on('disconnected', () =>
    console.log('Mongoose connection is disconnected.')
  )

  // close db connection when process exit 
  process.on('SIGINT', () => {
    mongoose.connection.close(() => {
      console.log('Mongoose connection is disconnected due to application termination.')
      process.exit(0)
    })
  })

  const connStr = "mongodb://localhost:27017/link_your_specialist";
  console.log('Establishing a Mongoose connection...')
  var connectWithRetry = function () {
    return mongoose.connect(connStr, function (err) {
      if (err) {
        console.error('Failed to connect to mongo on startup - retrying in 1 sec', err);
        setTimeout(connectWithRetry, 1000);
      } else {
        console.error('Connection established');
      }
    });
  };
  connectWithRetry();
}
module.exports = connectDB;