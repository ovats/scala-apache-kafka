# scala-apache-kafka

This is a very simple project for using Kafka and Scala in a local environment.
You can try starting server, create topics, remove topics, publish and consume messages and a lot more.

In this project:

- Scala 2.13.2
- SBT 1.3.10
- Zookeeper 3.4.9
- Kafka 2.5.0

So, I'm going to implement:

- one kafka consumer
- one kafka producer

# Docker

The project uses docker to run Kafka in the local environment.

To start Kafka:

- `docker-compose -f docker.yml up`

And to stop Kafka:

- `docker-compose -f docker.yml stop`

`Stop` doesn't remove containers. If you want to do that use `down` instead of `stop`. There's more info about `docker-compose` commands here [Overview of docker-compose CLI](https://docs.docker.com/compose/reference/overview/).
r-compose.

Don't credit me for `docker.yml` file. The credit goes to [Stephane Maarek](https://github.com/simplesteph) in [this](https://github.com/simplesteph/kafka-stack-docker-compose) project.

To clean used space (you will loose all your data in Kafka):

- `docker-compose -f docker.yml down`
- `rm -rf zk-single-kafka-single`

# Kafka

To create topics, removing, etc. you can choose from:

- [Kafka Tool](https://kafkatool.com/index.html)
- If you have installed Kafka in your local env use the tools in the `bin` directory. 

The first one is the easiest one. Just download the app, install it, add the the server and you can do whatever you want. I'm not going to detail, that's something you can do.

The second one you will send commands from the terminal.

## Commands for Kafka

After running Kafka you can list all topics with this command:

`kafka-topics.sh --zookeeper 127.0.0.1:2181 --list`

This command will show display all topics, your topics and topics created by Kafka itself.

To create a new topic with name `first_topic`:

`kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --create --partitions 3 --replication-factor 1`

I'm not going in all the details of this command but just in case if you noted that `replication-factor` is `1`, that's because this `docker-image` has only one `broker` running. If you try with `2` you will see an error.

You can check the topic has been created listing all topics again.

To list information about the topic we've just created you can you this command:

`kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --describe`

Now let's run one consumer and one producer from the command line. Open two terminal windows and run this command on each:

- `kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic first_topic`
- `kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic first_topic`

In the producer terminal type anything you want and press enter. Move to the consumer terminal you should see the same text that you've just typed.

Now we have our local Kafka up and running.
To stop consumer and producer just press Ctrl-C.

# Kafka Producer

A Kafka producer is implemented in `eventing.producer` package.
To test it:

- start Kafka using `docker-compose`
- start play application using `sbt run` in the project directory
- start a consumer (`kafka-console-consumer.sh`)
- use `curl` or `Postman` or any other app to hit the endpoint to publish the message

There are two endpoints available to push messages to Kafka

- `POST /publish/:message`
- `POST /publish`

If you want to use just `curl` try:

- `curl -X POST http://localhost:9000/publish/hello`

This endpoint will post words. If you want to publish complex text messages use:

- `curl -X POST http://localhost:9000/publish/ -H "Content-Type: application/json" -d '{"message":"this is a complex message"}'`

The producer takes two parameters from `application.conf`:

- `kafka.host`, now set to `localhost:9092`
- `kafka.producer.topic`, topic name to publish the message

That's it for the producer!

# Schedulers

Play Framework is based on Akka, and Akka bring schedulers available to Scala.
With schedulers we can execute task or send messages to actors, every 30 seconds, 10 minutes or the amount of time we want.
To implement a Kafka Consumer we are going to use schedulers. 

# Kafka Consumer

To do!


This is a project in progress.

