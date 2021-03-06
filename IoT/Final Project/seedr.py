import sqlite3
from faker import Faker
import paho.mqtt.client as mqtt
import tkinter

def seed_database():
    """
    Use this method to fill database with fake data.
    Faker lib is used to add worker's and card's name.
    :return: None
    """
    fake = Faker()
    con = sqlite3.connect('records.db')

    for _ in range(1000):
        con.execute(f'INSERT INTO workers (name) VALUES ("{fake.name()}")')
        con.execute(f'INSERT INTO cards (name) VALUES ("Card {fake.word()}")')

    con.commit()
    print('Tables was seeded successfully.')
    con.close()


if __name__ == '__main__':
    seed_database()