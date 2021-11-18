/**
* 메모리: 14004 KB, 시간: 100 ms
* 2021.11.18
* by Alub
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, ans = 0;
	static boolean visited[][][];
	static char[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int minsik[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C][1<<7];
		minsik = new int[2];
		
		for(int i=0; i<R; i++) {
			char[] c = in.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				map[i][j] = c[j];
				if(map[i][j] == '0') {
					minsik[0] = i;
					minsik[1] = j;
				}
			}
		}
	
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		int ret = 0;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {minsik[0], minsik[1], 0});
		visited[minsik[0]][minsik[1]][0] = true;
		
		int size, r, c, flag, nr, nc, nflag = 0;
		while(!q.isEmpty()) {
			size = q.size();
			for(int s=0; s<size; s++) {
				int cur[] = q.poll();
				r = cur[0];
				c = cur[1];
				flag = cur[2];
				
				if(map[r][c] == '1') {
					ans = ret;
					return;
				}
				for(int d=0; d<4; d++) {
					nr = r + dr[d];
					nc = c + dc[d];
					nflag = flag;
					if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc][flag] || map[nr][nc] == '#') continue;
					if(map[nr][nc] != '1' && map[nr][nc] !='.' && map[nr][nc] != '0') {
						if('A' <= map[nr][nc] && map[nr][nc] <= 'F') {
							int key = map[nr][nc] - 'A';
							if((flag & (1 << key)) == 0) {
								continue;
							}
 						}else {
 							nflag = flag | (1 << (map[nr][nc] - 'a'));
 						}
					}
					q.offer(new int[] {nr,nc, nflag});
					visited[nr][nc][flag] = true;
				}
			}
			ret++;
		}
		ans = -1;
	}
}