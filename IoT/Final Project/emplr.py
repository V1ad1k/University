import sqlite3
import sys
import tkinter as tk
from tkinter import ttk
import paho.mqtt.client as mqtt
from datetime import datetime
import csv
import time
import re
import os
from dotenv import load_dotenv


def run_server():
    load_dotenv()

    def on_message(client, userdata, message):
        msg = message.payload.decode("utf-8")
        if msg.isdigit():
            user = con.cursor().execute(f'SELECT * FROM workers WHERE card_id = {msg}').fetchall()
            con.execute(f'INSERT INTO records (card_id, worker_id) VALUES ({msg}, {user[0][0] if len(user) != 0 else "NULL"})')
            con.commit()
            print('Card ID_' + msg + ' time: ' + datetime.now().strftime("%d.%m.%Y %H:%M:%S"))
        else:
            print(msg)

    window = tk.Tk()
    window.title('Server')
    window.resizable(False, False)

    terminal_label = tk.Label(window, text='Terminal has not connected.')
    terminal_label.grid(column=0, row=0, columnspan=6, padx=15, sticky='nsew', pady=15)

    def add_worker():
        subwindow = tk.Tk()
        subwindow.title('New worker')
        subwindow.resizable(False, False)

        worker_name_textfield = tk.Entry(subwindow)
        worker_name_textfield.grid(column=3, row=1, columnspan=3, padx=(0, 15), pady=(15, 5), sticky='news')
        tk.Label(subwindow, text='Worker Name').grid(column=1, row=1, columnspan=1, padx=(15, 5), pady=(15, 5))

        def check_fields():
            worker_name = worker_name_textfield.get()
            if worker_name != '':
                con.execute(f'INSERT INTO workers (name) VALUES ("{worker_name}")')
                con.commit()
                terminal_label.config(text='Worker was added.')
            else:
                terminal_label.config(text='Name can not be empty.')
            subwindow.destroy()

        tk.Button(subwindow, text='New worker', command=check_fields).grid(column=3, row=4, columnspan=5, pady=(5, 15))
        subwindow.mainloop()

    def remove_worker():
        subwindow = tk.Tk()
        subwindow.title('Delete worker')
        subwindow.resizable(False, False)

        workers = list(
            map(lambda x: f'ID={x[0]}, {x[1]}', con.execute('SELECT * FROM workers ORDER BY name').fetchall()))
        workers_combobox = ttk.Combobox(subwindow, values=workers)
        workers_combobox.grid(column=3, row=1, columnspan=3, padx=(0, 15), pady=(15, 5), sticky='news')

        tk.Label(subwindow, text='Worker').grid(column=1, row=1, columnspan=1, padx=(15, 5), pady=(15, 5))

        def check_field():
            worker_id = list(map(int, re.findall(r'\d+', workers_combobox.get().split(',')[0])))[0]
            con.execute(f'DELETE FROM workers WHERE id = {worker_id}')
            con.commit()
            terminal_label.config(text='Worker has been Deleted.')
            subwindow.destroy()

        tk.Button(subwindow, text='Deleted worker', command=check_field).grid(column=4, row=4, pady=(5, 15))
        subwindow.mainloop()

    def pin_card():
        subwindow = tk.Tk()
        subwindow.title('Start/Finish card')
        subwindow.resizable(False, False)

        workers = list(map(lambda x: f'ID={x[0]}, {x[1]}',
                           con.execute('SELECT * FROM workers WHERE card_id IS NULL ORDER BY name').fetchall()))
        workers_combobox = ttk.Combobox(subwindow, values=workers)
        workers_combobox.grid(column=3, row=1, columnspan=3, padx=(0, 15), pady=(15, 5), sticky='news')

        cards = list(map(lambda x: f'ID={x[0]}, {x[1]}',
                         con.execute('SELECT * FROM cards WHERE worker_id IS NULL ORDER BY id').fetchall()))
        cards_combobox = ttk.Combobox(subwindow, values=cards)
        cards_combobox.grid(column=3, row=2, columnspan=3, padx=(0, 15), pady=(15, 5), sticky='news')

        tk.Label(subwindow, text='Worker').grid(column=1, row=1, columnspan=1, padx=(15, 5), pady=(15, 5))
        tk.Label(subwindow, text='Card').grid(column=1, row=2, columnspan=1, padx=(15, 5), pady=(15, 5))

        def check_field():
            worker_id = list(map(int, re.findall(r'\d+', workers_combobox.get().split(',')[0])))[0]
            card_id = list(map(int, re.findall(r'\d+', cards_combobox.get().split(',')[0])))[0]
            con.execute(f'UPDATE workers SET card_id = {card_id} WHERE id = {worker_id}')
            con.execute(f'UPDATE cards SET worker_id = {worker_id} WHERE id = {card_id}')
            con.commit()
            terminal_label.config(text='Card was pinned.')
            subwindow.destroy()

        tk.Button(subwindow, text='Start/Finish card to worker', command=check_field).grid(column=4, row=3, pady=(5, 15))
        subwindow.mainloop()

    def unpin_card():
        subwindow = tk.Tk()
        subwindow.title('Erase card')
        subwindow.resizable(False, False)

        workers = list(
            map(lambda x: f'ID={x[0]}, {x[1]}', con.execute('SELECT * FROM workers ORDER BY name').fetchall()))
        workers_combobox = ttk.Combobox(subwindow, values=workers)
        workers_combobox.grid(column=3, row=1, columnspan=3, padx=(0, 15), pady=(15, 5), sticky='news')

        tk.Label(subwindow, text='Worker').grid(column=1, row=1, columnspan=1, padx=(15, 5), pady=(15, 5))

        def check_field():
            worker_id = list(map(int, re.findall(r'\d+', workers_combobox.get().split(',')[0])))[0]
            con.execute(f'UPDATE workers SET card_id = NULL WHERE id = {worker_id}')
            con.commit()
            terminal_label.config(text='Card was unpinned.')
            subwindow.destroy()

        tk.Button(subwindow, text='Erase card', command=check_field).grid(column=4, row=4, pady=(5, 15))
        subwindow.mainloop()

    def make_report():
        records = con.cursor().execute('SELECT * FROM records').fetchall()
        with open('Output.csv', 'w', newline='') as f:
            wr = csv.DictWriter(f, fieldnames=['id', 'card_id', 'user_id', 'timestamp'])
            wr.writeheader()
            for record in records:
                wr.writerow({
                    "id": record[0],
                    "card_id": record[1],
                    "user_id": record[2] if record[2] else 'Anonymous',
                    "timestamp": record[3]
                })
            terminal_label.config(text='Report has been created.')

    add_worker_btn = tk.Button(window, text='New worker', command=add_worker)
    add_worker_btn.grid(column=0, row=1, sticky='nsew', padx=(15, 0))

    remove_worker_btn = tk.Button(window, text='Delete worker', command=remove_worker)
    remove_worker_btn.grid(column=1, row=1, sticky='nsew', padx=(0, 15))

    pin_card_btn = tk.Button(window, text='Start/Finish card', command=pin_card)
    pin_card_btn.grid(column=0, row=2, sticky='nsew', padx=(15, 0))

    unpin_card_btn = tk.Button(window, text='Erase card', command=unpin_card)
    unpin_card_btn.grid(column=1, row=2, sticky='nsew', padx=(0, 15))

    make_report_btn = tk.Button(window, text='Create report', command=make_report)
    make_report_btn.grid(column=0, row=3, sticky='nsew', padx=(15, 0), pady=(0, 15))

    def stop():
        client.loop_stop()
        client.disconnect()
        con.close()
        sys.exit(0)

    stop_btn = tk.Button(window, text='Exit', command=stop)
    stop_btn.grid(column=1, row=3, sticky='nsew', pady=(0, 15), padx=(0, 15))

    def on_connect(client, ud, flags, rc):
        terminal_label.config(text=mqtt.connack_string(rc))
        if rc == 5:
            add_worker_btn.config(state='disabled')
            pin_card_btn.config(state='disabled')
            stop_btn.config(state='disabled')
            remove_worker_btn.config(state='disabled')
            unpin_card_btn.config(state='disabled')
            make_report_btn.config(state='disabled')

    client = mqtt.Client()
    client.tls_set(os.getenv('CERTIFICATE'))
    client.username_pw_set(username=os.getenv('SERVER_USER'), password=os.getenv('SERVER_PASS'))
    client.on_connect = on_connect
    client.on_message = on_message

    con = sqlite3.connect(os.getenv('DB_NAME'), check_same_thread=False)

    while True:
        try:
            client.connect(os.getenv('BROKER'), int(os.getenv('PORT')))
            break
        except ConnectionRefusedError:
            print('Connection error.')
        time.sleep(1)

    client.loop_start()
    client.subscribe(os.getenv('TOPIC_CARD'))
    client.subscribe('auth/login')

    window.mainloop()


if __name__ == '__main__':
    run_server()