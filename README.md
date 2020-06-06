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

This is a project in progress.

