import { Injectable } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private stompClient!: Stomp.Client;
  private inputsSubject = new BehaviorSubject<any[]>([]);
  inputs$ = this.inputsSubject.asObservable();

  constructor() {
    this.connect();
  }

  private connect() {
    const socket = new SockJS.default('http://localhost:8082/ws');
    this.stompClient = new Stomp.Client({
      brokerURL: 'ws://localhost:8082/ws',
      connectHeaders: {},
      debug: (str) => console.log(str),
      reconnectDelay: 5000,
    });

    this.stompClient.activate();

    this.stompClient.onConnect = () => {
      this.stompClient.subscribe('/topic/angular', (message) => {
        const data = JSON.parse(message.body);
        this.inputsSubject.next(data);
      });
    };
  }
}
