from flask import Flask, render_template, Response
import redis
import json


app = Flask(__name__)
r = redis.StrictRedis(host='127.0.0.1', port=6379, db=0)


def event_stream():
    pubsub = r.pubsub()
    pubsub.subscribe('data')
    for m in pubsub.listen():
        print m
        print 'Recieved: {0}'.format(m['data'])


@app.route('/stream')
def stream():
    return Response(event_stream(), mimetype="text/event-stream")

if __name__ == '__main__':
    app.run(threaded=True,
    host='0.0.0.0'
)
